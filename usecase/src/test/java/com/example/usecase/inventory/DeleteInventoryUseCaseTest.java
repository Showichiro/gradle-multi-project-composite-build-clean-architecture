package com.example.usecase.inventory;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.inventory.gateway.IInventoryGateway;

@ExtendWith(MockitoExtension.class)
class DeleteInventoryUseCaseTest {

  @Mock private IInventoryGateway inventoryGateway;

  private DeleteInventoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteInventoryUseCase(inventoryGateway);
  }

  @Test
  void deleteInventory_CallsGatewayDelete() {
    // Arrange
    Long inventoryId = 1L;

    // Act
    useCase.deleteInventory(inventoryId);

    // Assert
    verify(inventoryGateway).delete(inventoryId);
  }
}
