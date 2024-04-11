package com.sntzgrr.controllers;


import com.sntzgrr.dto.UserRegistrationRecord;
import com.sntzgrr.services.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class KeycloakUserApi {
    private final KeycloakUserService keycloakUserService;

    @GetMapping
    public UserRepresentation getUser(Principal principal){
        return keycloakUserService.getUserById(principal.getName());
    }

    @PostMapping
    public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord){
        return keycloakUserService.createUser(userRegistrationRecord);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        keycloakUserService.deleteUserById(userId);
    }
}
