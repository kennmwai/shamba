package com.kenm.spring.farmclientservice.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String firstName;
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email must be a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
