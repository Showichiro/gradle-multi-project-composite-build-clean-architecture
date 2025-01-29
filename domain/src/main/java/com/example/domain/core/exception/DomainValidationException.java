package com.example.domain.core.exception;

public class DomainValidationException extends IllegalArgumentException {
    public DomainValidationException(String message) {
        super(message);
    }
}