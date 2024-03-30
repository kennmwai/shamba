package com.kenm.spring.farmclientservice.mapper.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kenm.spring.farmclientservice.dto.UserRoleDTO;
import com.kenm.spring.farmclientservice.mapper.UserRoleMapper;
import com.kenm.spring.farmclientservice.models.UserRole;

@Component
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public  UserRoleDTO toUserRoleDTO(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return new UserRoleDTO(userRole.getId(), userRole.getName());
    }

    @Override
    public UserRole toUserRole(UserRoleDTO userRoleDTO) {
        
        if (userRoleDTO == null) {
            return null;
        }
        return new UserRole(userRoleDTO.getId(), userRoleDTO.getName());
    }

    @Override
    public Set<UserRoleDTO> toUserRoleDTOs (Set<UserRole> userRoles) {
        
        if (userRoles == null) {
            return null;
        }
        Set<UserRoleDTO> userRoleDTOs = userRoles.stream().map(this::toUserRoleDTO).collect(Collectors.toSet());

        return userRoleDTOs;
    }

    @Override
    public Set<UserRole> toUserRoles (Set<UserRoleDTO> userRoleDTOs) {
        
        if (userRoleDTOs == null) {
            return null;
        }
        Set<UserRole> userRoles = userRoleDTOs.stream().map(this::toUserRole).collect(Collectors.toSet());

        return userRoles;
    }

}
