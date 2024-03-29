package com.kenm.spring.farmclientservice.mapper;

import java.util.List;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.models.User;

public interface UserMapper {

    UserDTO mapUserToUserDTO(User user);

    User mapUserDTOToUser(UserDTO userDTO);

    List<UserDTO> mapUsersToUserDTOs(List<User> users);

    List<User> mapUserDTOsToUsers(List<UserDTO> userDTOs);

}
