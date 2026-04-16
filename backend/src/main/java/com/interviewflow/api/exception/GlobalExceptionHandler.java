package com.interviewflow.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), 404, LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), 400, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorized(UnauthorizedException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), 401, LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentials(BadCredentialsException ex) {
        return new ResponseEntity<>(
                new ApiError("Invalid email or password", 401, LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(
                new ApiError("Authentication failed", 401, LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        return new ResponseEntity<>(
                new ApiError("Internal server error", 500, LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}