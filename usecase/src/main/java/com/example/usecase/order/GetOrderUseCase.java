package com.example.usecase.order;

import com.example.domain.order.exception.OrderNotFoundException;
import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;

public class GetOrderUseCase {
  private final IOrderGateway orderGateway;

  public GetOrderUseCase(IOrderGateway orderGateway) {
    this.orderGateway = orderGateway;
  }

  public Order getOrder(Long id) throws OrderNotFoundException {
    return orderGateway.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
  }
}
