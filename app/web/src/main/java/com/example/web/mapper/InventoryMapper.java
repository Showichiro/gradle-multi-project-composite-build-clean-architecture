package com.example.web.mapper;

import com.example.domain.inventory.model.Inventory;
import com.example.web.entity.InventorySchema;

public class InventoryMapper {
    private InventoryMapper() {
    }

    public static InventorySchema fromInventory(Inventory inventory) {
        return new InventorySchema(inventory);
    }

    public static Inventory toInventory(InventorySchema schema) {
        var inventory = new Inventory(schema.getId(), schema.getName(), schema.getQuantity());
        return inventory;
    }
}