package main.java.com.example.shop.service;

public class DomainExceptions {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String m) { super(m); }
    }
    public static class ValidationException extends RuntimeException {
        public ValidationException(String m) { super(m); }
    }
    public static class ConflictException extends RuntimeException {
        public ConflictException(String m) { super(m); }
    }
    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String m) { super(m); }
    }
}
