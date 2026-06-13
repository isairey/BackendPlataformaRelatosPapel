package com.relatospapel.bookspayments.exception;

import com.relatospapel.bookspayments.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiError> handleInvalidData(InvalidDataException ex, HttpServletRequest request) {
        log.warn("Se genero un excepcion...");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiError(ex.getMessage(), 409, request.getRequestURI(), Instant.now())
        );
    }
}
