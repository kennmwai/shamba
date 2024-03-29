package com.kenm.spring.farmclientservice.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.entities.User;
import com.kenm.spring.farmclientservice.mapper.UserMapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO mapUserToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public User mapUserDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    @Override
    public List<UserDTO> mapUsersToUserDTOs(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserDTO> userDTOs = users.stream()
                                    .map(user -> mapUserToUserDTO(user))
                                    .collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public List<User> mapUserDTOsToUsers(List<UserDTO> userDTOs) {
        if (userDTOs == null) {
            return null;
        }

        List<User> users = userDTOs.stream()
                                    .map(userDTO -> mapUserDTOToUser(userDTO))
                                    .collect(Collectors.toList());
        return users;
    }
    
}
