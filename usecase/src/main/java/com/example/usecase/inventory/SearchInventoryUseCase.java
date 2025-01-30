package com.example.usecase.inventory;

import java.util.List;

import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.usecase.inventory.dto.IInventorySearchData;

public class SearchInventoryUseCase {
  private final IInventoryGateway inventoryGateway;

  public SearchInventoryUseCase(IInventoryGateway inventoryGateway) {
    this.inventoryGateway = inventoryGateway;
  }

  public List<Inventory> searchInventories(IInventorySearchData data) {
    return inventoryGateway.findAll(data.size(), data.page());
  }
}
