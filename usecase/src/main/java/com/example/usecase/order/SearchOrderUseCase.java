package com.example.usecase.order;

import java.util.List;

import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;
import com.example.usecase.order.dto.IOrderSearchData;

public class SearchOrderUseCase {
  private final IOrderGateway orderGateway;

  public SearchOrderUseCase(IOrderGateway orderGateway) {
    this.orderGateway = orderGateway;
  }

  public List<Order> searchOrders(IOrderSearchData data) {
    return orderGateway.findAll(data.size(), data.page());
  }
}
