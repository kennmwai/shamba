package com.kenm.spring.farmclientservice.mapper.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.dto.UserRoleDTO;
import com.kenm.spring.farmclientservice.mapper.UserMapper;
import com.kenm.spring.farmclientservice.models.User;
import com.kenm.spring.farmclientservice.models.UserRole;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserRoleMapperImpl roleMapper;

    @Override
    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        if (user.getRoles() != null) {
            Set<UserRoleDTO> userRoleDTOs = roleMapper.toUserRoleDTOs(user.getRoles());
            userDTO.setRoles(userRoleDTOs);
        }
        return userDTO;
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        if (userDTO.getRoles() != null) {
            Set<UserRole> userRoles = roleMapper.toUserRoles(userDTO.getRoles());
            user.setRoles(userRoles);
        }

        return user;
    }

    @Override
    public List<UserDTO> toUserDTOs(List<User> users) {
        if (users == null) {
            return null;
        }

        return users.parallelStream()
                    .map(this::toUserDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<User> toUsers(List<UserDTO> userDTOs) {
        if (userDTOs == null) {
            return null;
        }

        return userDTOs.parallelStream()
                    .map(this::toUser)
                    .collect(Collectors.toList());
    }

}
