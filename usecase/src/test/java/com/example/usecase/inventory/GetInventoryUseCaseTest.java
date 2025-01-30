package com.example.usecase.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.inventory.exception.InventoryNotFoundException;
import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;

@ExtendWith(MockitoExtension.class)
class GetInventoryUseCaseTest {

  @Mock private IInventoryGateway inventoryGateway;

  private GetInventoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetInventoryUseCase(inventoryGateway);
  }

  @Test
  void getInventory_ExistingId_ReturnsInventory() throws Exception {
    // Arrange
    Long inventoryId = 1L;
    String name = "Test Item";
    int quantity = 10;
    Inventory expectedInventory = new Inventory(inventoryId, name, quantity);
    when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(expectedInventory));

    // Act
    Inventory result = useCase.getInventory(inventoryId);

    // Assert
    assertEquals(inventoryId, result.getId());
    assertEquals(name, result.getName());
    assertEquals(quantity, result.getQuantity());
  }

  @Test
  void getInventory_NonExistingId_ThrowsInventoryNotFoundException() {
    // Arrange
    Long inventoryId = 999L;
    when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(InventoryNotFoundException.class, () -> useCase.getInventory(inventoryId));
  }
}
