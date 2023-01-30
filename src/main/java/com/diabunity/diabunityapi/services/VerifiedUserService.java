package com.diabunity.diabunityapi.services;

import java.util.Arrays;

public class VerifiedUserService {

    public static boolean isVerified(String userId) {
        String[] userVerified = System.getenv("VERIFIED_USERS").split(",");
        return Arrays.stream(userVerified).filter(x -> x.equals(userId)).count() > 0;
    }
}
