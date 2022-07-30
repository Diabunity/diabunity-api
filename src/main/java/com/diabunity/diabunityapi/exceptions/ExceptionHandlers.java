package com.diabunity.diabunityapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ InvalidUserTokenException.class })
    public ResponseEntity handleInvalidUserTokenException(InvalidUserTokenException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "user token is invalid", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }

    @ExceptionHandler ( {NotFoundException.class} )
    public ResponseEntity handleNotFoundException(NotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }

    @ExceptionHandler ( {BadRequestException.class} )
    public ResponseEntity handleBadRequestException(BadRequestException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getCauses());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }

    @Override
    public ResponseEntity handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getCode());
    }

}

