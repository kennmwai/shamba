package com.kenm.spring.farmclientservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kenm.spring.farmclientservice.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(?1)")
    Optional<User> findByEmailIgnoreCase(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(?1)")
    Optional<User> findByUsernameIgnoreCase(String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(?1) OR LOWER(u.email) = LOWER(?1)")
    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String email);

    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
