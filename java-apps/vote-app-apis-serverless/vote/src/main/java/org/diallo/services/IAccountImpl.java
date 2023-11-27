package org.diallo.services;

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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

@Service
public class IAccountImpl implements IAccount {
    private static final String FIREBASE_API_URL_UP = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyDwT-pgSCdcLRc9pRI3LuG78URsPqavvng";
    private static final String FIREBASE_API_URL_IN = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDwT-pgSCdcLRc9pRI3LuG78URsPqavvng";

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
}