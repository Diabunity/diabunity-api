package com.diabunity.diabunityapi.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String cause) {
        super(cause);
    }
}
