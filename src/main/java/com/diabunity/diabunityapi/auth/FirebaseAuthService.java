package com.diabunity.diabunityapi.auth;

import com.diabunity.diabunityapi.DiabunityApiApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class FirebaseAuthService {
    @Primary
    @Bean
    public FirebaseApp getFirebaseApp() throws IOException {
        ClassLoader classloader = DiabunityApiApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classloader.getSystemResource("diabunity-dev-firebase-adminsdk-bg41i-bf2a2eb992.json")).getFile());

        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
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
