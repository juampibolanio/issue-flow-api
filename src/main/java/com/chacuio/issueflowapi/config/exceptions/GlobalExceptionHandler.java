package com.chacuio.issueflowapi.config.exceptions;

import com.chacuio.issueflowapi.users.exceptions.EmailAlreadyExistsException;
import com.chacuio.issueflowapi.users.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage() + ": " + ex.getCause())
                .status(HttpStatus.NOT_FOUND.value())
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage() + ":" + ex.getCause())
                .status(HttpStatus.CONFLICT.value())
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message("An error has occurred: " + ex.getCause())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
