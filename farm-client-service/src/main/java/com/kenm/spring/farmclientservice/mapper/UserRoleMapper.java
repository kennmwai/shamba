package com.kenm.spring.farmclientservice.mapper;

import java.util.Set;

import com.kenm.spring.farmclientservice.dto.UserRoleDTO;
import com.kenm.spring.farmclientservice.models.UserRole;

public interface UserRoleMapper {

    UserRoleDTO toUserRoleDTO(UserRole userRole);

    UserRole toUserRole(UserRoleDTO userRoleDTO);

    Set<UserRoleDTO> toUserRoleDTOs (Set<UserRole> userRoles);

    Set<UserRole> toUserRoles(Set<UserRoleDTO> userRoleDTOs);
}
