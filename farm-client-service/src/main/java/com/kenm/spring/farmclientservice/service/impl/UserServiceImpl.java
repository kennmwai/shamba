package com.kenm.spring.farmclientservice.service.impl;

import com.kenm.spring.farmclientservice.dto.UserDTO;
import com.kenm.spring.farmclientservice.exception.UserAlreadyExistsException;
import com.kenm.spring.farmclientservice.exception.UserNotFoundException;
import com.kenm.spring.farmclientservice.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    }

    @Override
    public UserDTO getUserByUsername(String username) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUsername'");
    }

    @Override
    public UserDTO createUser(UserDTO user) throws UserAlreadyExistsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
