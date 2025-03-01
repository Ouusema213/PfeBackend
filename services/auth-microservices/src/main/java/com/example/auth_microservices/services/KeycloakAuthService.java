package com.example.auth_microservices.services;

import com.example.auth_microservices.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloakAuthService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public LoginResponse authenticate(String username, String password) {
        String url = keycloakServerUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        // Création du body de la requête
        Map<String, String> body = new HashMap<>();
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("grant_type", "password");
        body.put("username", username);
        body.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setAccessToken((String) responseBody.get("access_token"));
                loginResponse.setRefreshToken((String) responseBody.get("refresh_token"));
                loginResponse.setExpiresIn((Integer) responseBody.get("expires_in"));
                return loginResponse;
            } else {
                throw new RuntimeException("Échec de l'authentification");
            }
        } catch (Exception e) {
            throw new RuntimeException("Utilisateur non trouvé ou mot de passe incorrect");
        }
    }
}
