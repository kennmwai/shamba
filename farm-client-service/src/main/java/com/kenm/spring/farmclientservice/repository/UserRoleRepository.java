package com.kenm.spring.farmclientservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenm.spring.farmclientservice.models.UserRole;
import com.kenm.spring.farmclientservice.models.enums.EUserRole;

public interface UserRoleRepository  extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(EUserRole name);

}
