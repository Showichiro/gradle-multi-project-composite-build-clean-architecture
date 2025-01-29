package com.example.usecase.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.usecase.inventory.dto.TestInventorySearchData;

@ExtendWith(MockitoExtension.class)
class SearchInventoryUseCaseTest {

    @Mock
    private IInventoryGateway inventoryGateway;

    private SearchInventoryUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SearchInventoryUseCase(inventoryGateway);
    }

    @Test
    void searchInventories_WithResults_ReturnsInventoryList() {
        // Arrange
        int size = 10;
        int page = 0;
        TestInventorySearchData data = new TestInventorySearchData(size, page);

        Inventory inventory1 = new Inventory(1L, "Item 1", 10);
        Inventory inventory2 = new Inventory(2L, "Item 2", 20);
        List<Inventory> expectedInventories = Arrays.asList(inventory1, inventory2);

        when(inventoryGateway.findAll(size, page)).thenReturn(expectedInventories);

        // Act
        List<Inventory> result = useCase.searchInventories(data);

        // Assert
        assertEquals(2, result.size());
        assertEquals(expectedInventories, result);
        verify(inventoryGateway).findAll(size, page);
    }

    @Test
    void searchInventories_NoResults_ReturnsEmptyList() {
        // Arrange
        int size = 10;
        int page = 0;
        TestInventorySearchData data = new TestInventorySearchData(size, page);

        when(inventoryGateway.findAll(size, page)).thenReturn(Collections.emptyList());

        // Act
        List<Inventory> result = useCase.searchInventories(data);

        // Assert
        assertTrue(result.isEmpty());
        verify(inventoryGateway).findAll(size, page);
    }

    @Test
    void searchInventories_DifferentPageAndSize_PassesCorrectParameters() {
        // Arrange
        int size = 20;
        int page = 2;
        TestInventorySearchData data = new TestInventorySearchData(size, page);

        when(inventoryGateway.findAll(size, page)).thenReturn(Collections.emptyList());

        // Act
        useCase.searchInventories(data);

        // Assert
        verify(inventoryGateway).findAll(size, page);
    }
}
