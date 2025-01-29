package com.example.usecase.inventory.dto;

public record TestInventoryRegistrationData(String name, int quantity) implements IInventoryRegistrationData {
}
