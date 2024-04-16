package com.sntzgrr.services;

import com.sntzgrr.dto.User;
import com.sntzgrr.repositories.FeignUserServiceRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserServiceImpl implements KeycloakUserService{

    @Value("${keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;
    private final FeignUserServiceRepository feignUserServiceRepository;
    private static final String ALIAS_FILE_PATH = "/alias.txt";

    @Override
    public User createUser(User userRegis) {
        String alias = generateAlias();
        String cvu = generateCVU();

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(alias);
        user.setEmail(userRegis.getEmail());
        user.setFirstName(userRegis.getFirstName());
        user.setLastName(userRegis.getLastName());
        user.setEmailVerified(true);

        Map<String, List<String>> attributes = new LinkedHashMap<>();
        attributes.put("dni", Collections.singletonList(userRegis.getDni()));
        attributes.put("phone", Collections.singletonList(userRegis.getPhone()));
        attributes.put("cvu", Collections.singletonList(cvu));
        attributes.put("alias", Collections.singletonList(alias));
        user.setAttributes(attributes);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRegis.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);

        userRegis.setCvu(cvu);
        userRegis.setAlias(alias);
        userRegis.setPassword(null);
        feignUserServiceRepository.createUser(userRegis);

        if (Objects.equals(201, response.getStatus())){
            return new User(
                    userRegis.getId(),
                    userRegis.getFirstName(),
                    userRegis.getLastName(),
                    userRegis.getDni(),
                    userRegis.getEmail(),
                    userRegis.getPhone(),
                    userRegis.getCvu(),
                    userRegis.getAlias(),
                    null);
        }

        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);
    }

    private String generateAlias() {
        List<String> aliases;
        try {
            InputStream inputStream = getClass().getResourceAsStream(ALIAS_FILE_PATH);
            if (inputStream == null) {
                log.error("Alias file not found in classpath.");
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            aliases = new ArrayList<>(reader.lines().toList());
            reader.close();
        } catch (IOException e) {
            log.error("Error reading alias file: {}", e.getMessage());
            return null;
        }
        Collections.shuffle(aliases);
        while (aliases.size() < 3) {
            List<String> copy = new ArrayList<>(aliases);
            aliases.addAll(copy);
        }
        return String.join(".", aliases.subList(0, 3));
    }

    public static String generateCVU() {
        String characters = "0123456789";
        StringBuilder cvu = new StringBuilder();
        int longCVU = 22;
        Random rand = new Random();
        for (int i = 0; i < longCVU; i++) {
            int index = rand.nextInt(characters.length());
            cvu.append(characters.charAt(index));
        }
        return cvu.toString();
    }
}
