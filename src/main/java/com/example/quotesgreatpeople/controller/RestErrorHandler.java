package com.example.quotesgreatpeople.controller;

import com.example.quotesgreatpeople.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionResponse> handleStatusException(RuntimeException ex, WebRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getClass().getName())
                .message(ex.getMessage())
                .path(request.getDescription(false).substring(4))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
