package com.chacuio.issueflowapi.common.exception;

import com.chacuio.issueflowapi.common.exception.dto.ErrorResponseDTO;
import com.chacuio.issueflowapi.tickets.exception.TicketAlreadyClosedException;
import com.chacuio.issueflowapi.users.exception.EmailAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // ticket exceptions
    @ExceptionHandler(TicketAlreadyClosedException.class)
    public ResponseEntity<ErrorResponseDTO> handleTicketAlreadyClosedException(TicketAlreadyClosedException ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // user exceptions
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // common exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                .orElse("Invalid input data");

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message("Validation failed: " + details)
                .status(HttpStatus.BAD_REQUEST.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest req) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message("An internal server error has occurred. Please contact support.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now(Clock.systemUTC()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
