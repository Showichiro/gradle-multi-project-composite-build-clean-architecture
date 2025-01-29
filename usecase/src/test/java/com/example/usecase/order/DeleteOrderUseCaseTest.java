package com.example.usecase.order;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.order.gateway.IOrderGateway;

@ExtendWith(MockitoExtension.class)
class DeleteOrderUseCaseTest {

  @Mock private IOrderGateway orderGateway;

  private DeleteOrderUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteOrderUseCase(orderGateway);
  }

  @Test
  void deleteOrder_CallsGatewayDelete() {
    // Arrange
    Long orderId = 1L;

    // Act
    useCase.deleteOrder(orderId);

    // Assert
    verify(orderGateway).delete(orderId);
  }
}
