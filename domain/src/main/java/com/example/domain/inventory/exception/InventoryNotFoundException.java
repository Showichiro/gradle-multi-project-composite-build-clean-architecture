package com.example.domain.inventory.exception;

import com.example.domain.core.exception.NotFoundException;

public class InventoryNotFoundException extends NotFoundException {
    public InventoryNotFoundException(Long id) {
        super("inventory-" + id + " is not found");
    }
}
