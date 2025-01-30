package com.example.usecase.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.core.exception.DomainValidationException;
import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.usecase.inventory.dto.TestInventoryRegistrationData;

@ExtendWith(MockitoExtension.class)
class CreateInventoryUseCaseTest {

  @Mock private IInventoryGateway inventoryGateway;

  private CreateInventoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateInventoryUseCase(inventoryGateway);
  }

  @Test
  void createInventory_ValidData_CreatesInventory() {
    // Arrange
    String name = "Test Item";
    int quantity = 10;
    TestInventoryRegistrationData data = new TestInventoryRegistrationData(name, quantity);

    Long createdId = 1L;
    Inventory createdInventory = new Inventory(createdId, name, quantity);
    when(inventoryGateway.create(any(Inventory.class))).thenReturn(createdInventory);

    // Act
    Inventory result = useCase.createInventory(data);

    // Assert
    verify(inventoryGateway).create(any(Inventory.class));
    assertEquals(createdId, result.getId());
    assertEquals(name, result.getName());
    assertEquals(quantity, result.getQuantity());
  }

  @Test
  void createInventory_NullName_ThrowsDomainValidationException() {
    // Arrange
    String name = null;
    int quantity = 10;
    TestInventoryRegistrationData data = new TestInventoryRegistrationData(name, quantity);

    // Act & Assert
    assertThrows(DomainValidationException.class, () -> useCase.createInventory(data));
  }

  @Test
  void createInventory_EmptyName_ThrowsDomainValidationException() {
    // Arrange
    String name = "";
    int quantity = 10;
    TestInventoryRegistrationData data = new TestInventoryRegistrationData(name, quantity);

    // Act & Assert
    assertThrows(DomainValidationException.class, () -> useCase.createInventory(data));
  }

  @Test
  void createInventory_BlankName_ThrowsDomainValidationException() {
    // Arrange
    String name = "   ";
    int quantity = 10;
    TestInventoryRegistrationData data = new TestInventoryRegistrationData(name, quantity);

    // Act & Assert
    assertThrows(DomainValidationException.class, () -> useCase.createInventory(data));
  }

  @Test
  void createInventory_NegativeQuantity_ThrowsDomainValidationException() {
    // Arrange
    String name = "Test Item";
    int quantity = -1;
    TestInventoryRegistrationData data = new TestInventoryRegistrationData(name, quantity);

    // Act & Assert
    assertThrows(DomainValidationException.class, () -> useCase.createInventory(data));
  }
}
