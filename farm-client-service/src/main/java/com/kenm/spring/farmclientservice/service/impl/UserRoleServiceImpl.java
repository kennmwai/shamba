package com.kenm.spring.farmclientservice.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenm.spring.farmclientservice.dto.UserRoleDTO;
import com.kenm.spring.farmclientservice.mapper.impl.UserRoleMapperImpl;
import com.kenm.spring.farmclientservice.models.enums.EUserRole;
import com.kenm.spring.farmclientservice.repository.UserRoleRepository;
import com.kenm.spring.farmclientservice.service.UserRoleService;

@Component
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;

    @Autowired
    private UserRoleMapperImpl roleMapper;
    @Override
    public UserRoleDTO findByName(EUserRole name) {
        return Optional.ofNullable(name)
                .flatMap(roleRepository::findByName)
                .map(roleMapper::toUserRoleDTO)
                .orElse(null);
    }

    @Override
    public UserRoleDTO createRole(UserRoleDTO userRoleDTO) {
        return Optional.ofNullable(userRoleDTO)
                .map(roleMapper::toUserRole)
                .filter(userRole -> !roleRepository.findByName(userRole.getName()).isPresent())
                .map(roleRepository::save)
                .map(roleMapper::toUserRoleDTO)
                .orElse(null);
    }

    @Override
    public UserRoleDTO updateRole(UserRoleDTO userRoleDTO) {
        if (userRoleDTO == null) {
            return null;
        }
        return roleRepository.findById(userRoleDTO.getId())
                .map(existingRole -> {
                    existingRole.setName(userRoleDTO.getName());
                    return roleMapper.toUserRoleDTO(roleRepository.save(existingRole));
                })
                .orElse(null);
    }

    @Override
    public UserRoleDTO deleteRole(UserRoleDTO userRoleDTO) {
        if (userRoleDTO == null) {
            return null;
        }
        return roleRepository.findById(userRoleDTO.getId())
                .map(role -> {
                    roleRepository.delete(role);
                    return roleMapper.toUserRoleDTO(role);
                })
                .orElse(null);
    }

    @Override
    public Set<UserRoleDTO> getAllRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::toUserRoleDTO)
                .collect(Collectors.toSet());
    }
}
