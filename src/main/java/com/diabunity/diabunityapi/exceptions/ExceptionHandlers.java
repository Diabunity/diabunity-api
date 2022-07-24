package com.diabunity.diabunityapi.exceptions;

import com.diabunity.diabunityapi.services.InvalidUserTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ InvalidUserTokenException.class })
    public ResponseEntity handleInvalidUserTokenException(InvalidUserTokenException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "invalid ´auth-token´ header", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }
}

