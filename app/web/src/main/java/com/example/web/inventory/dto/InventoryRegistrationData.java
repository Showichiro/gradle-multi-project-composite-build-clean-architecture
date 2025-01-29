package com.example.web.inventory.dto;

import com.example.usecase.inventory.dto.IInventoryRegistrationData;
import com.example.web.inventory.validation.UniqueName;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InventoryRegistrationData(
        @NotBlank @UniqueName String name,
        @Min(0) int quantity) implements IInventoryRegistrationData {
}