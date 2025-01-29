package com.example.web.inventory.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.web.repository.InventoryRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !inventoryRepository.existsByName(value);
    }
}