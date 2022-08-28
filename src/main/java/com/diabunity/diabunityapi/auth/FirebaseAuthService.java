package com.diabunity.diabunityapi.auth;

import com.diabunity.diabunityapi.DiabunityApiApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

@Configuration
public class FirebaseAuthService {

    @Primary
    @Bean
    public FirebaseApp getFirebaseApp() throws IOException {
        ClassLoader classloader = DiabunityApiApplication.class.getClassLoader();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(classloader.getResourceAsStream("diabunity-dev-firebase-adminsdk-bg41i-bf2a2eb992.json")))
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
