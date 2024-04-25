package com.metlife.controller;


import com.metlife.dto.AuthRequest;
import com.metlife.dto.AuthResponse;
import com.metlife.dto.UserCredentialDto;
import com.metlife.entity.Roles;
import com.metlife.entity.UserCredential;
import com.metlife.repository.RolesRepository;
import com.metlife.repository.UserCredentialRepository;
//import com.metlife.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthControllers {

    private static final Logger logger = LoggerFactory.getLogger(AuthControllers.class);

//    @Autowired
//    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserCredentialDto userCredentialDto) {
        logger.info("Received registration request for username: {}", userCredentialDto.getUserName());

        // Check if username already exists in the database
        if (userCredentialRepository.existsByUserName(userCredentialDto.getUserName())) {
            logger.warn("Username {} already exists", userCredentialDto.getUserName());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Username is already taken!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Check if email already exists in the database
        if (userCredentialRepository.existsByEmail(userCredentialDto.getEmail())) {
            logger.warn("Email {} already exists", userCredentialDto.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email is already taken!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Create a new user entity
        UserCredential user = new UserCredential();
        user.setUserName(userCredentialDto.getUserName());
        user.setEmail(userCredentialDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCredentialDto.getPassword()));

        // Assign a role to the user (e.g., ROLE_USER, ROLE_ADMIN)
        Roles role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() ->
                new RuntimeException("Role not found"));

        user.setRoles(Collections.singleton(role));

        // Save the user entity to the database
        userCredentialRepository.save(user);

        logger.info("User registered successfully with username: {}", userCredentialDto.getUserName());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
