package com.kenm.spring.farmclientservice.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.entities.User;
import com.kenm.spring.farmclientservice.exception.UserAlreadyExistsException;
import com.kenm.spring.farmclientservice.exception.UserNotFoundException;
import com.kenm.spring.farmclientservice.mapper.impl.UserMapperImpl;
import com.kenm.spring.farmclientservice.repository.UserRepository;
import com.kenm.spring.farmclientservice.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapperImpl userMapper;
    

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return Collections.emptyList();
        }
        return userMapper.mapUsersToUserDTOs(users);
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id)
                .map(userMapper::mapUserToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException {
        if (email == null) {
            return null;
        }
        return userRepository.findByEmailIgnoreCase(email)
                .map(userMapper::mapUserToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserDTO getUserByUsername(String username) throws UserNotFoundException {
        if (username == null) {
            return null;
        }
        return userRepository.findByUsernameIgnoreCase(username)
                .map(userMapper::mapUserToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserDTO createUser(UserDTO userDto) throws UserAlreadyExistsException {
        if (userDto == null) {
            return null;
        }
        String username = userDto.getUsername();
        String email = userDto.getEmail();
        
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }
        
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new UserAlreadyExistsException("User already exists with email: " + email);
        }
        
        User newUser = userMapper.mapUserDTOToUser(userDto);
        if (newUser == null) {
            return null;
        }
        newUser = userRepository.save(newUser);
        
        return userMapper.mapUserToUserDTO(newUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) throws UserNotFoundException {
        if (userDto == null || id == null) {
            throw new IllegalArgumentException("User ID and DTO must not be null");
        }

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        BeanUtils.copyProperties(userDto, existingUser, "id");

        User updatedUser = userRepository.save(existingUser);
        return userMapper.mapUserToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        userRepository.deleteById(id);
        userRepository.flush();
    }
}
