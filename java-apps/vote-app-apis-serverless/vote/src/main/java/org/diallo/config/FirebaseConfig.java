package org.diallo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseConfig {

    @PostConstruct
    public void configureFirebaseCon() throws IOException {
        File file = ResourceUtils.getFile("classpath:config/diallo-koula-shop-firebase-adminsdk-hjvit-677802c5c6.json");
        FileInputStream serviceAccount =
                new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://diallo-koula-shop-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

    }
}