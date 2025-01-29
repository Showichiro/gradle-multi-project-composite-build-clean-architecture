package com.example.domain.order.exception;

import com.example.domain.core.exception.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
  public OrderNotFoundException(Long id) {
    super("order-" + id + " is not found");
  }
}
