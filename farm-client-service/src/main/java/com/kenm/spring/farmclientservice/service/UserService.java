package com.kenm.spring.farmclientservice.service;

import java.util.List;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.exception.UserAlreadyExistsException;
import com.kenm.spring.farmclientservice.exception.UserNotFoundException;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id) throws UserNotFoundException;

    UserDTO getUserByEmail(String email) throws UserNotFoundException;

    UserDTO getUserByUsername(String username) throws UserNotFoundException;

    UserDTO createUser(UserDTO user) throws UserAlreadyExistsException;

    UserDTO updateUser(Long id, UserDTO user) throws UserNotFoundException;

    void deleteUser(Long id) throws UserNotFoundException;
}
