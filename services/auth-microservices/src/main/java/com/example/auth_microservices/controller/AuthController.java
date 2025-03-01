package com.example.auth_microservices.controller;

import com.example.auth_microservices.dto.LoginRequest;
import com.example.auth_microservices.dto.LoginResponse;
import com.example.auth_microservices.services.KeycloakAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private KeycloakAuthService keycloakAuthService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return keycloakAuthService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    }
}