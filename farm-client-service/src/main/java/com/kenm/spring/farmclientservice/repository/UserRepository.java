package com.kenm.spring.farmclientservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kenm.spring.farmclientservice.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(?1)")
    Optional<User> findByEmailIgnoreCase(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(?1)")
    Optional<User> findByUsernameIgnoreCase(String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(?1) OR LOWER(u.email) = LOWER(?1)")
    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(?1)")
    boolean existsByUsernameIgnoreCase(String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(?1)")
    boolean existsByEmailIgnoreCase(String email);

    // Optional<User> findByPasswordResetToken(String passwordResetToken);

    // Optional<User> findByVerificationToken(String verificationToken);

    // Optional<User> findByStatus(String status);

    // Optional<User> findByRole(String role);

    // Optional<User> findByRoleAndStatus(String role, String status);

    // Optional<User> findByRoleAndStatusAndName(String role, String status, String name);

    // Optional<User> findByStatusAndName(String status, String name);

    // Optional<User> findByStatusAndEmail(String status, String email);

    // Optional<User> findByEmailAndRole(String email, String role);

    // Optional<User> findByRoleAndEmail(String role, String email);
}
