package com.example.auth_microservices.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakAdminService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.admin.username}")
    private String adminUsername;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    public void createUser(String username, String firstName, String lastName, String email, String password, String role) {
        // Connexion à Keycloak en tant qu'administrateur
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm("master")  // On s'authentifie d'abord dans le "master" realm
                .clientId("admin-cli")
                .username(adminUsername)
                .password(adminPassword)
                .build();

        try {
            RealmResource realmResource = keycloak.realm(keycloakRealm);
            UsersResource usersResource = realmResource.users();

            // Vérification si l'utilisateur existe déjà
            List<UserRepresentation> existingUsers = usersResource.search(username, true);
            if (!existingUsers.isEmpty()) {
                System.out.println("L'utilisateur existe déjà !");
                return;
            }

            // Création de l'utilisateur
            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setEnabled(true);

            Response response = usersResource.create(user);

            if (response.getStatus() == 409) {
                System.out.println("L'utilisateur existe déjà (409 Conflict).");
                return;
            } else if (response.getStatus() != 201) {
                throw new RuntimeException("Échec de la création de l'utilisateur : " + response.getStatus());
            }

            // Récupération de l'ID utilisateur
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            // Définition du mot de passe
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);

            usersResource.get(userId).resetPassword(credential);

            // Attribution du rôle
            RoleRepresentation roleRepresentation = realmResource.roles().get(role).toRepresentation();
            usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(roleRepresentation));

            System.out.println("Utilisateur créé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
        } finally {
            keycloak.close();  // Fermeture de la connexion
        }
    }
}