package com.diabunity.diabunityapi.services;

public class InvalidUserTokenException extends Exception {
    public InvalidUserTokenException(String cause) {
        super(cause);
    }
}
