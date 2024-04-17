package com.kenm.spring.farmclientservice.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.dto.UserRoleDTO;
import com.kenm.spring.farmclientservice.exception.UserAlreadyExistsException;
import com.kenm.spring.farmclientservice.exception.UserNotFoundException;
import com.kenm.spring.farmclientservice.mapper.impl.UserMapperImpl;
import com.kenm.spring.farmclientservice.mapper.impl.UserRoleMapperImpl;
import com.kenm.spring.farmclientservice.models.User;
import com.kenm.spring.farmclientservice.repository.UserRepository;
import com.kenm.spring.farmclientservice.service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapperImpl userMapper;

    @Autowired
    private UserRoleServiceImpl roleService;

    @Autowired
    private UserRoleMapperImpl roleMapper;


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return Collections.emptyList();
        }
        return userMapper.toUserDTOs(users);
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException {
        if (email == null) {
            return null;
        }
        return userRepository.findByEmailIgnoreCase(email)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserDTO getUserByUsername(String username) throws UserNotFoundException {
        if (username == null) {
            return null;
        }
        return userRepository.findByUsernameIgnoreCase(username)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserDTO createUser(UserDTO userDto) throws UserAlreadyExistsException {
        if (userDto == null) {
            throw new IllegalArgumentException("User DTO must not be null");
        }

        if (userDto.getId() != null) {
            throw new IllegalArgumentException("User ID must be null when creating a new user");
        }

        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role");
        }

        String username = userDto.getUsername();
        String email = userDto.getEmail();

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User already exists with email: " + email);
        }

        // Ensuring only one role is used for the new user.
        // This assumes the business logic is such that a new user should have exactly one role.
        // If the business logic allows for multiple roles, this section should be adapted accordingly.
        UserRoleDTO roleDTO = userDto.getRoles().iterator().next();
        UserRoleDTO existingRoleDTO = roleService.findByName(roleDTO.getName());
        userDto.setRoles(Collections.singleton(existingRoleDTO != null ? existingRoleDTO : roleService.createRole(roleDTO)));

        User newUser = userMapper.toUser(userDto);
        if (newUser.getId() != null) {
            throw new IllegalArgumentException("User ID must be null when creating a new user");
        }
        newUser = userRepository.save(newUser);

        return userMapper.toUserDTO(newUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDto) throws UserNotFoundException {
        if (userDto == null || userId == null) {
            throw new IllegalArgumentException("User ID and DTO must not be null");
        }

        return userRepository.findById(userId).map(existingUser -> {
            Set<UserRoleDTO> userRoles = userDto.getRoles();
            if (userRoles == null || userRoles.isEmpty()) {
                throw new IllegalArgumentException("User must have at least one role");
            }

            // Update the user's roles
            UserRoleDTO newRole = userRoles.iterator().next();
            UserRoleDTO updatedRole = roleService.updateRole(newRole);
            if (updatedRole == null) {
                updatedRole = roleService.createRole(newRole);
            }

            Set<UserRoleDTO> rolesToSet = Collections.singleton(updatedRole);
            userDto.setRoles(rolesToSet);
            existingUser.setRoles(roleMapper.toUserRoles(rolesToSet));

            BeanUtils.copyProperties(userDto, existingUser, "id");
            return userMapper.toUserDTO(userRepository.save(existingUser));
        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        userRepository.flush();
    }
}
