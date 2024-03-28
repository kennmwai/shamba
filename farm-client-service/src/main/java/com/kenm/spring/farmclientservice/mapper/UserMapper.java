package com.kenm.spring.farmclientservice.mapper;

import java.util.List;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.entities.User;

public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> userToUserDTO(List<User> user);

    List<User> userDTOToUser(List<UserDTO> userDTO);

}
