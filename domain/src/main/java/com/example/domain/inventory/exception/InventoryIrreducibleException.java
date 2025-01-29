package com.example.domain.inventory.exception;

import com.example.domain.core.exception.BadRequestException;

public class InventoryIrreducibleException extends BadRequestException {
    public InventoryIrreducibleException() {
        super("this inventory is not reducible");
    }
}
