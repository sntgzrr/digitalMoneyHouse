package com.sntzgrr.services;

import com.sntzgrr.dto.User;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {
    User createUser(User user);
    UserRepresentation getUserById (String userId);
    void deleteUserById (String userId);
}
