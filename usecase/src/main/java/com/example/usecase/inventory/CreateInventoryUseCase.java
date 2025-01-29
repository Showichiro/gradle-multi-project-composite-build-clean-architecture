package com.example.usecase.inventory;

import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.usecase.inventory.dto.IInventoryRegistrationData;

public class CreateInventoryUseCase {
    private final IInventoryGateway inventoryGateway;

    public CreateInventoryUseCase(IInventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public Inventory createInventory(IInventoryRegistrationData data) {
        Inventory inventory = new Inventory(null, data.name(), data.quantity());
        return inventoryGateway.create(inventory);
    }
}
