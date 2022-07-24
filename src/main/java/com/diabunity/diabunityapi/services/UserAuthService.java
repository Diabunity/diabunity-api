package com.diabunity.diabunityapi.services;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    public String batata() throws Exception {
        String customToken = firebaseAuthService.getAuth().createCustomToken("QB8Xkm6BjTfPoLtUQZ6Lr67m7yk1");
        return customToken;
    }

    public String getUserIDFromAuthToken(String userToken) throws InvalidUserTokenException {
        try {
            FirebaseToken decodedToken = firebaseAuthService.getAuth().verifyIdToken(userToken);
            return decodedToken.getUid();
        } catch (Exception e) {
            throw new InvalidUserTokenException(e.getMessage());
        }
    }
}
