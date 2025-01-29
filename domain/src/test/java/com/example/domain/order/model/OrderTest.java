package com.example.domain.order.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.domain.core.exception.DomainValidationException;

class OrderTest {

  @Test
  void constructor_ValidValuesWithoutInventoryName_CreatesInstance() {
    // Arrange
    Long id = 1L;
    int quantity = 5;
    String customerInfo = "Test Customer";
    Long inventoryId = 100L;

    // Act
    Order order = new Order(id, quantity, customerInfo, inventoryId);

    // Assert
    assertEquals(id, order.getId());
    assertEquals(quantity, order.getQuantity());
    assertEquals(customerInfo, order.getCustomerInfo());
    assertEquals(inventoryId, order.getInventoryId());
    assertNull(order.getInventoryName());
  }

  @Test
  void constructor_ValidValuesWithInventoryName_CreatesInstance() {
    // Arrange
    Long id = 1L;
    int quantity = 5;
    String customerInfo = "Test Customer";
    Long inventoryId = 100L;
    String inventoryName = "Test Item";

    // Act
    Order order = new Order(id, quantity, customerInfo, inventoryId, inventoryName);

    // Assert
    assertEquals(id, order.getId());
    assertEquals(quantity, order.getQuantity());
    assertEquals(customerInfo, order.getCustomerInfo());
    assertEquals(inventoryId, order.getInventoryId());
    assertEquals(inventoryName, order.getInventoryName());
  }

  @Test
  void constructor_NegativeQuantity_ThrowsDomainValidationException() {
    // Arrange
    Long id = 1L;
    int quantity = -1;
    String customerInfo = "Test Customer";
    Long inventoryId = 100L;

    // Act & Assert
    assertThrows(
        DomainValidationException.class, () -> new Order(id, quantity, customerInfo, inventoryId));
  }

  @Test
  void constructor_NullCustomerInfo_ThrowsDomainValidationException() {
    // Arrange
    Long id = 1L;
    int quantity = 5;
    String customerInfo = null;
    Long inventoryId = 100L;

    // Act & Assert
    assertThrows(
        DomainValidationException.class, () -> new Order(id, quantity, customerInfo, inventoryId));
  }

  @Test
  void constructor_EmptyCustomerInfo_ThrowsDomainValidationException() {
    // Arrange
    Long id = 1L;
    int quantity = 5;
    String customerInfo = "";
    Long inventoryId = 100L;

    // Act & Assert
    assertThrows(
        DomainValidationException.class, () -> new Order(id, quantity, customerInfo, inventoryId));
  }

  @Test
  void constructor_BlankCustomerInfo_ThrowsDomainValidationException() {
    // Arrange
    Long id = 1L;
    int quantity = 5;
    String customerInfo = "   ";
    Long inventoryId = 100L;

    // Act & Assert
    assertThrows(
        DomainValidationException.class, () -> new Order(id, quantity, customerInfo, inventoryId));
  }
}
