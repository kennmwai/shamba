package com.kenm.spring.farmclientservice.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    // @NotBlank(message = "First name is required")
    // private String firstName;

    // @NotBlank(message = "Last name is required")
    // private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email must be a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Set<UserRoleDTO> roles = new HashSet<>();

	private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime lastModifiedAt;

    private String lastModifiedBy;
}
