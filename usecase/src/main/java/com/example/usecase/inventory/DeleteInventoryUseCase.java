package com.example.usecase.inventory;

import com.example.domain.inventory.gateway.IInventoryGateway;

public class DeleteInventoryUseCase {

  private final IInventoryGateway inventoryGateway;

  public DeleteInventoryUseCase(IInventoryGateway inventoryGateway) {
    this.inventoryGateway = inventoryGateway;
  }

  public void deleteInventory(Long id) {
    inventoryGateway.delete(id);
  }
}
