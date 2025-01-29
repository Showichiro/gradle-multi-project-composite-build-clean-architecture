package com.example.web.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.inventory.exception.InventoryNotFoundException;
import com.example.usecase.inventory.GetInventoryUseCase;
import com.example.web.inventory.dto.InventoryPublicData;

@RestController
public class GetInventoryController {
    @Autowired
    private GetInventoryUseCase getInventoryUseCase;

    @GetMapping("inventories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryPublicData getInventory(@PathVariable Long id) throws InventoryNotFoundException {
        return InventoryPublicData.fromInventory(getInventoryUseCase.getInventory(id));
    }
}