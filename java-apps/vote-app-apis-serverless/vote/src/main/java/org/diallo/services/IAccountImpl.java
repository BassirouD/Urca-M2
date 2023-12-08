package org.diallo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.diallo.entities.FirebaseUser;
import org.diallo.entities.FirebaseUserDTO;
import org.diallo.entities.FirebaseUserSignIn;
import org.diallo.services.interfaces.IAccount;
import org.diallo.tools.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

@Service
public class IAccountImpl implements IAccount {
    private static final String FIREBASE_API_URL_UP = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyDwT-pgSCdcLRc9pRI3LuG78URsPqavvng";
    private static final String FIREBASE_API_URL_IN = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDwT-pgSCdcLRc9pRI3LuG78URsPqavvng";

    Logger logger = LoggerFactory.getLogger(IAccountImpl.class);

    @Override
    public FirebaseUserDTO signUp(FirebaseUser firebaseUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FirebaseUser> requestEntity = new HttpEntity<>(firebaseUser, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FirebaseUser> responseEntity = restTemplate.exchange(
                FIREBASE_API_URL_UP,
                HttpMethod.POST,
                requestEntity,
                FirebaseUser.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            FirebaseUserDTO userDTO = new FirebaseUserDTO();
            userDTO.setEmail(responseEntity.getBody().getEmail());
            return userDTO;
        }
        if (responseEntity.getStatusCode().is4xxClientError()) {
            throw new RuntimeException("error de création");
        } else {
            throw new RuntimeException("error de création");
        }
    }

    @Override
    public FirebaseUserSignIn signIn(FirebaseUser firebaseUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FirebaseUser> requestEntity = new HttpEntity<>(firebaseUser, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FirebaseUserSignIn> responseEntity = restTemplate.exchange(
                FIREBASE_API_URL_IN,
                HttpMethod.POST,
                requestEntity,
                FirebaseUserSignIn.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("error authentication");
        }
    }

    @Override
    public FirebaseUserDTO signUp2(FirebaseUser firebaseUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FirebaseUser> requestEntity = new HttpEntity<>(firebaseUser, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<FirebaseUser> responseEntity = restTemplate.exchange(
                    FIREBASE_API_URL_UP,
                    HttpMethod.POST,
                    requestEntity,
                    FirebaseUser.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                FirebaseUserDTO userDTO = new FirebaseUserDTO();
                userDTO.setEmail(responseEntity.getBody().getEmail());
                logger.info("From logger info=========>: Regestration done successfully!!!");
                return userDTO;
            } else if (responseEntity.getStatusCode().is4xxClientError()) {
                logger.error("From logger error 400=========>:");
                return null;
            } else if (responseEntity.getStatusCode().is5xxServerError()) {
                logger.error("From logger error 500=========>:");
                return null;
            } else {
                logger.error("From logger error nada=========>:");
                return null;
            }
        } catch (HttpClientErrorException ex) {
            logger.error("From logger HttpClientErrorException=========>:", ex.getStatusCode());
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                // Gérer l'erreur Firebase
                String responseBody = ex.getResponseBodyAsString();
                // Vous pouvez utiliser une bibliothèque JSON (par exemple, Jackson) pour analyser la réponse
                // et extraire les détails de l'erreur.
                // En supposant que responseBody contient une représentation JSON de l'erreur.
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Map<String, Object> errorResponse = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
                    });
                    String errorCode = (String) errorResponse.get("error");
                    String errorMessage = (String) errorResponse.get("message");

                    // Gérer l'erreur spécifique "EMAIL_EXISTS"
                    if ("EMAIL_EXISTS".equals(errorCode)) {
                        // Faire quelque chose avec cette erreur
                        // Par exemple, logger un message ou renvoyer une exception personnalisée.
                        throw new RuntimeException("L'adresse e-mail existe déjà.");
                    } else {
                        // Gérer d'autres erreurs si nécessaire
                        throw new RuntimeException("Erreur inattendue lors de la création de l'utilisateur : " + errorMessage);
                    }
                } catch (IOException e) {
                    // Gérer les erreurs d'analyse JSON si nécessaire
                    throw new RuntimeException("Erreur lors de l'analyse de la réponse JSON : " + e.getMessage(), e);
                }
            } else {
                // Gérer d'autres erreurs HTTP si nécessaire
                throw new RuntimeException("Erreur inattendue lors de la création de l'utilisateur : " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public FirebaseUserDTO signUp3(FirebaseUser firebaseUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FirebaseUser> requestEntity = new HttpEntity<>(firebaseUser, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<FirebaseUser> responseEntity = restTemplate.exchange(
                    FIREBASE_API_URL_UP,
                    HttpMethod.POST,
                    requestEntity,
                    FirebaseUser.class
            );
            FirebaseUserDTO userDTO = new FirebaseUserDTO();
            userDTO.setEmail(responseEntity.getBody().getEmail());
            logger.info("From logger info=========>: Regestration done successfully!!!");
            return userDTO;
        }catch (Exception exception){
            logger.error("From logger error exception=========>:");
            throw new RuntimeException("Error");
        }
    }

}