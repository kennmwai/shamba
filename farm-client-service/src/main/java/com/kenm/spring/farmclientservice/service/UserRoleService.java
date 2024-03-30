package com.kenm.spring.farmclientservice.service;

import java.util.Set;

import com.kenm.spring.farmclientservice.dto.UserRoleDTO;
import com.kenm.spring.farmclientservice.models.enums.EUserRole;

public interface UserRoleService {

    UserRoleDTO findByName(EUserRole name);

    UserRoleDTO createRole(UserRoleDTO userRoleDTO);

    UserRoleDTO updateRole(UserRoleDTO userRoleDTO);

    UserRoleDTO deleteRole(UserRoleDTO userRoleDTO);

    Set<UserRoleDTO> getAllRoles();

}
