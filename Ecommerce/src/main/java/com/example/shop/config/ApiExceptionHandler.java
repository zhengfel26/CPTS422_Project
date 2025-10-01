package main.java.com.example.shop.config;

import main.java.com.example.shop.service.DomainExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(DomainExceptions.NotFoundException.class)
    public ResponseEntity<?> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload("NOT_FOUND", ex.getMessage()));
    }
    @ExceptionHandler(DomainExceptions.ValidationException.class)
    public ResponseEntity<?> handleValidation(RuntimeException ex) {
        return ResponseEntity.badRequest().body(payload("VALIDATION_ERROR", ex.getMessage()));
    }
    @ExceptionHandler(DomainExceptions.ConflictException.class)
    public ResponseEntity<?> handleConflict(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(payload("CONFLICT", ex.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(payload("INTERNAL_ERROR", ex.getMessage()));
    }

    private Map<String,Object> payload(String code, String message) {
        return Map.of("code", code, "message", message, "requestId", UUID.randomUUID().toString());
    }
}
