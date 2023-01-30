package com.diabunity.diabunityapi.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import java.io.ByteArrayInputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

@Configuration
public class FirebaseAuthService {

    @Primary
    @Bean
    public FirebaseApp getFirebaseApp() throws IOException {

        String firebaseConfig = System.getenv("FIREBASE_CONFIG").replace("*", "\\n");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials
                        .fromStream(
                        new ByteArrayInputStream(firebaseConfig.getBytes())))
                .build();

        FirebaseApp.initializeApp(options);
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirebaseApp.getInstance();
    }

    @Bean
    public FirebaseAuth getAuth() throws IOException {
        return FirebaseAuth.getInstance(getFirebaseApp());
    }
}
