package com.example.domain.core.exception;

public class BadRequestException extends Exception {
  public BadRequestException() {}

  public BadRequestException(String message) {
    super(message);
  }
}
