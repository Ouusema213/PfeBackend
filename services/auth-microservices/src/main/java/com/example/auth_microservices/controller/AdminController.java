package com.example.auth_microservices.controller;

import com.example.auth_microservices.dto.UserDto;
import com.example.auth_microservices.services.KeycloakAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private KeycloakAdminService keycloakAdminService;

    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDto userDto) {
        keycloakAdminService.createUser(
                userDto.getUsername(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()
        );
        return "User created successfully";
    }
}
