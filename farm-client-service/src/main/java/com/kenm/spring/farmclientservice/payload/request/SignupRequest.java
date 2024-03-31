package com.kenm.spring.farmclientservice.payload.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "Email is required")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> role;
    
}
