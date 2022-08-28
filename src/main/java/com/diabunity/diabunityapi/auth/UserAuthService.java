package com.diabunity.diabunityapi.auth;

import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private FirebaseAuthService firebaseAuthService;
    
    public String getUserIDFromAuthToken(String userToken) throws InvalidUserTokenException {
        try {
            FirebaseToken decodedToken = firebaseAuthService.getAuth().verifyIdToken(userToken);
            return decodedToken.getUid();
        } catch (Exception e) {
            throw new InvalidUserTokenException(e.getMessage());
        }
    }
}
