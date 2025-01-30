package com.example.usecase.inventory;

import com.example.domain.inventory.exception.InventoryNotFoundException;
import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;

public class GetInventoryUseCase {
  private final IInventoryGateway inventoryGateway;

  public GetInventoryUseCase(IInventoryGateway inventoryGateway) {
    this.inventoryGateway = inventoryGateway;
  }

  public Inventory getInventory(Long id) throws InventoryNotFoundException {
    return inventoryGateway.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
  }
}
