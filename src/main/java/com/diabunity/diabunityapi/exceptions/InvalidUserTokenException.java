package com.diabunity.diabunityapi.exceptions;

public class InvalidUserTokenException extends Exception {

    public InvalidUserTokenException() {}
    public InvalidUserTokenException(String cause) {
        super(cause);
    }
}
