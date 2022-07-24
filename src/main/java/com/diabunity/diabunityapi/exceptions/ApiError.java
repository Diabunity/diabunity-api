package com.diabunity.diabunityapi.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private HttpStatus code;
    private String message;
    private List<String> cause;

    public ApiError(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError(HttpStatus code, String description, String cause) {
        this.code = code;
        this.message = description;
        this.cause = Arrays.asList(cause);
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getCause() {
        return cause;
    }

    public void setCause(List<String> cause) {
        this.cause = cause;
    }
}