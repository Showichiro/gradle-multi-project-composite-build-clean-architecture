package com.example.usecase.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.order.exception.OrderNotFoundException;
import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;

@ExtendWith(MockitoExtension.class)
class GetOrderUseCaseTest {

  @Mock private IOrderGateway orderGateway;

  private GetOrderUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetOrderUseCase(orderGateway);
  }

  @Test
  void getOrder_ExistingId_ReturnsOrder() throws Exception {
    // Arrange
    Long orderId = 1L;
    int quantity = 5;
    String customerInfo = "Test Customer";
    Long inventoryId = 100L;
    Order expectedOrder = new Order(orderId, quantity, customerInfo, inventoryId);
    when(orderGateway.findById(orderId)).thenReturn(Optional.of(expectedOrder));

    // Act
    Order result = useCase.getOrder(orderId);

    // Assert
    assertEquals(orderId, result.getId());
    assertEquals(quantity, result.getQuantity());
    assertEquals(customerInfo, result.getCustomerInfo());
    assertEquals(inventoryId, result.getInventoryId());
  }

  @Test
  void getOrder_NonExistingId_ThrowsOrderNotFoundException() {
    // Arrange
    Long orderId = 999L;
    when(orderGateway.findById(orderId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(OrderNotFoundException.class, () -> useCase.getOrder(orderId));
  }
}
