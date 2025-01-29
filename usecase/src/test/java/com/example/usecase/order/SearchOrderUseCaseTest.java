package com.example.usecase.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;
import com.example.usecase.order.dto.TestOrderSearchData;

@ExtendWith(MockitoExtension.class)
class SearchOrderUseCaseTest {

  @Mock private IOrderGateway orderGateway;

  private SearchOrderUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new SearchOrderUseCase(orderGateway);
  }

  @Test
  void searchOrders_WithResults_ReturnsOrderList() {
    // Arrange
    int size = 10;
    int page = 0;
    TestOrderSearchData data = new TestOrderSearchData(size, page);

    Order order1 = new Order(1L, 5, "Customer 1", 100L);
    Order order2 = new Order(2L, 3, "Customer 2", 101L);
    List<Order> expectedOrders = Arrays.asList(order1, order2);

    when(orderGateway.findAll(size, page)).thenReturn(expectedOrders);

    // Act
    List<Order> result = useCase.searchOrders(data);

    // Assert
    assertEquals(2, result.size());
    assertEquals(expectedOrders, result);
    verify(orderGateway).findAll(size, page);
  }

  @Test
  void searchOrders_NoResults_ReturnsEmptyList() {
    // Arrange
    int size = 10;
    int page = 0;
    TestOrderSearchData data = new TestOrderSearchData(size, page);

    when(orderGateway.findAll(size, page)).thenReturn(Collections.emptyList());

    // Act
    List<Order> result = useCase.searchOrders(data);

    // Assert
    assertTrue(result.isEmpty());
    verify(orderGateway).findAll(size, page);
  }

  @Test
  void searchOrders_DifferentPageAndSize_PassesCorrectParameters() {
    // Arrange
    int size = 20;
    int page = 2;
    TestOrderSearchData data = new TestOrderSearchData(size, page);

    when(orderGateway.findAll(size, page)).thenReturn(Collections.emptyList());

    // Act
    useCase.searchOrders(data);

    // Assert
    verify(orderGateway).findAll(size, page);
  }
}
