package com.example.usecase.order;

import com.example.domain.order.gateway.IOrderGateway;

public class DeleteOrderUseCase {
  private final IOrderGateway orderGateway;

  public DeleteOrderUseCase(IOrderGateway orderGateway) {
    this.orderGateway = orderGateway;
  }

  public void deleteOrder(Long id) {
    orderGateway.delete(id);
  }
}
