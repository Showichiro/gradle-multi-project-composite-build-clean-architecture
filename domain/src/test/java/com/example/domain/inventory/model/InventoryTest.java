package com.example.domain.inventory.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.domain.core.exception.DomainValidationException;

class InventoryTest {

    @Test
    void constructor_ValidValues_CreatesInstance() {
        // Arrange
        Long id = 1L;
        String name = "Test Item";
        int quantity = 10;

        // Act
        Inventory inventory = new Inventory(id, name, quantity);

        // Assert
        assertEquals(id, inventory.getId());
        assertEquals(name, inventory.getName());
        assertEquals(quantity, inventory.getQuantity());
    }

    @Test
    void constructor_NullName_ThrowsDomainValidationException() {
        // Arrange
        Long id = 1L;
        String name = null;
        int quantity = 10;

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> new Inventory(id, name, quantity));
    }

    @Test
    void constructor_EmptyName_ThrowsDomainValidationException() {
        // Arrange
        Long id = 1L;
        String name = "";
        int quantity = 10;

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> new Inventory(id, name, quantity));
    }

    @Test
    void constructor_BlankName_ThrowsDomainValidationException() {
        // Arrange
        Long id = 1L;
        String name = "   ";
        int quantity = 10;

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> new Inventory(id, name, quantity));
    }

    @Test
    void constructor_NegativeQuantity_ThrowsDomainValidationException() {
        // Arrange
        Long id = 1L;
        String name = "Test Item";
        int quantity = -1;

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> new Inventory(id, name, quantity));
    }

    @Test
    void reducible_QuantityGreaterThanAmount_ReturnsTrue() {
        // Arrange
        Inventory inventory = new Inventory(1L, "Test Item", 10);
        int amount = 5;

        // Act
        boolean result = inventory.reducible(amount);

        // Assert
        assertTrue(result);
    }

    @Test
    void reducible_QuantityEqualToAmount_ReturnsTrue() {
        // Arrange
        Inventory inventory = new Inventory(1L, "Test Item", 10);
        int amount = 10;

        // Act
        boolean result = inventory.reducible(amount);

        // Assert
        assertTrue(result);
    }

    @Test
    void reducible_QuantityLessThanAmount_ReturnsFalse() {
        // Arrange
        Inventory inventory = new Inventory(1L, "Test Item", 5);
        int amount = 10;

        // Act
        boolean result = inventory.reducible(amount);

        // Assert
        assertFalse(result);
    }

    @Test
    void reduce_ValidAmount_ReturnsNewInstanceWithReducedQuantity() {
        // Arrange
        Long id = 1L;
        String name = "Test Item";
        int initialQuantity = 10;
        int reduceAmount = 3;
        Inventory inventory = new Inventory(id, name, initialQuantity);

        // Act
        Inventory reducedInventory = inventory.reduce(reduceAmount);

        // Assert
        assertEquals(id, reducedInventory.getId());
        assertEquals(name, reducedInventory.getName());
        assertEquals(initialQuantity - reduceAmount, reducedInventory.getQuantity());
        
        // 元のインスタンスは変更されていないことを確認
        assertEquals(initialQuantity, inventory.getQuantity());
    }
}
