package com.kenm.spring.farmclientservice.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenm.spring.farmclientservice.models.User;
import com.kenm.spring.farmclientservice.models.UserRole;
import com.kenm.spring.farmclientservice.models.enums.EUserRole;
import com.kenm.spring.farmclientservice.payload.request.LoginRequest;
import com.kenm.spring.farmclientservice.payload.request.SignupRequest;
import com.kenm.spring.farmclientservice.payload.response.JwtResponse;
import com.kenm.spring.farmclientservice.payload.response.MessageResponse;
import com.kenm.spring.farmclientservice.repository.UserRepository;
import com.kenm.spring.farmclientservice.repository.UserRoleRepository;
import com.kenm.spring.farmclientservice.security.jwt.JwtUtils;
import com.kenm.spring.farmclientservice.security.services.UserDetailsImpl;
import com.kenm.spring.farmclientservice.service.impl.UserRoleServiceImpl;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository roleRepository;

    @Autowired
    UserRoleServiceImpl roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roleNames = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse(token, "Bearer", userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roleNames);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        // Set user's role
        Set<String> strRoles = signUpRequest.getRole();
        Set<UserRole> roles = new HashSet<>();

        if (strRoles == null) {
        UserRole userRole = roleRepository.findByName(EUserRole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        } else {
        strRoles.forEach(role -> {
            switch (role) {
            case "admin":
            UserRole adminRole = roleRepository.findByName(EUserRole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
            case "mod":
            UserRole modRole = roleRepository.findByName(EUserRole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
            default:
            UserRole userRole = roleRepository.findByName(EUserRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            }
        });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> fetchAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

}
