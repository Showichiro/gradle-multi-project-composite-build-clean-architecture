package com.example.usecase.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.core.exception.DomainValidationException;
import com.example.domain.inventory.exception.InventoryIrreducibleException;
import com.example.domain.inventory.exception.InventoryNotFoundException;
import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;
import com.example.usecase.order.dto.TestOrderRegistrationData;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private IOrderGateway orderGateway;

    @Mock
    private IInventoryGateway inventoryGateway;

    private CreateOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateOrderUseCase(orderGateway, inventoryGateway);
    }

    @Test
    void createOrder_ValidData_CreatesOrderAndUpdatesInventory() throws Exception {
        // Arrange
        Long inventoryId = 1L;
        String inventoryName = "Test Item";
        int initialQuantity = 10;
        int orderQuantity = 5;
        String customerInfo = "Test Customer";

        // 在庫の準備
        Inventory inventory = new Inventory(inventoryId, inventoryName, initialQuantity);
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(inventory));

        // 在庫更新後のモック
        Inventory updatedInventory = new Inventory(inventoryId, inventoryName, initialQuantity - orderQuantity);
        when(inventoryGateway.update(any(Inventory.class))).thenReturn(updatedInventory);

        // 注文作成後のモック
        Long orderId = 100L;
        Order createdOrder = new Order(orderId, orderQuantity, customerInfo, inventoryId);
        when(orderGateway.create(any(Order.class))).thenReturn(createdOrder);

        TestOrderRegistrationData data = new TestOrderRegistrationData(orderQuantity, customerInfo);

        // Act
        Order result = useCase.createOrder(inventoryId, data);

        // Assert
        verify(inventoryGateway).findById(inventoryId);
        verify(inventoryGateway).update(any(Inventory.class));
        verify(orderGateway).create(any(Order.class));
        
        assertEquals(orderId, result.getId());
        assertEquals(orderQuantity, result.getQuantity());
        assertEquals(customerInfo, result.getCustomerInfo());
        assertEquals(inventoryId, result.getInventoryId());
    }

    @Test
    void createOrder_InventoryNotFound_ThrowsInventoryNotFoundException() {
        // Arrange
        Long inventoryId = 1L;
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.empty());

        TestOrderRegistrationData data = new TestOrderRegistrationData(5, "Test Customer");

        // Act & Assert
        assertThrows(InventoryNotFoundException.class,
                () -> useCase.createOrder(inventoryId, data));
    }

    @Test
    void createOrder_InsufficientInventory_ThrowsInventoryIrreducibleException() {
        // Arrange
        Long inventoryId = 1L;
        Inventory inventory = new Inventory(inventoryId, "Test Item", 5);
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(inventory));

        TestOrderRegistrationData data = new TestOrderRegistrationData(10, "Test Customer");

        // Act & Assert
        assertThrows(InventoryIrreducibleException.class,
                () -> useCase.createOrder(inventoryId, data));
    }

    @Test
    void createOrder_NegativeQuantity_ThrowsDomainValidationException() {
        // Arrange
        Long inventoryId = 1L;
        String inventoryName = "Test Item";
        int initialQuantity = 10;
        Inventory inventory = new Inventory(inventoryId, inventoryName, initialQuantity);
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(inventory));
        when(inventoryGateway.update(any(Inventory.class))).thenReturn(inventory);

        TestOrderRegistrationData data = new TestOrderRegistrationData(-1, "Test Customer");

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> useCase.createOrder(inventoryId, data));
    }

    @Test
    void createOrder_NullCustomerInfo_ThrowsDomainValidationException() {
        // Arrange
        Long inventoryId = 1L;
        String inventoryName = "Test Item";
        int initialQuantity = 10;
        Inventory inventory = new Inventory(inventoryId, inventoryName, initialQuantity);
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(inventory));
        when(inventoryGateway.update(any(Inventory.class))).thenReturn(inventory);

        TestOrderRegistrationData data = new TestOrderRegistrationData(5, null);

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> useCase.createOrder(inventoryId, data));
    }

    @Test
    void createOrder_EmptyCustomerInfo_ThrowsDomainValidationException() {
        // Arrange
        Long inventoryId = 1L;
        String inventoryName = "Test Item";
        int initialQuantity = 10;
        Inventory inventory = new Inventory(inventoryId, inventoryName, initialQuantity);
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(inventory));
        when(inventoryGateway.update(any(Inventory.class))).thenReturn(inventory);

        TestOrderRegistrationData data = new TestOrderRegistrationData(5, "");

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> useCase.createOrder(inventoryId, data));
    }

    @Test
    void createOrder_BlankCustomerInfo_ThrowsDomainValidationException() {
        // Arrange
        Long inventoryId = 1L;
        String inventoryName = "Test Item";
        int initialQuantity = 10;
        Inventory inventory = new Inventory(inventoryId, inventoryName, initialQuantity);
        when(inventoryGateway.findById(inventoryId)).thenReturn(Optional.of(inventory));
        when(inventoryGateway.update(any(Inventory.class))).thenReturn(inventory);

        TestOrderRegistrationData data = new TestOrderRegistrationData(5, "   ");

        // Act & Assert
        assertThrows(DomainValidationException.class,
                () -> useCase.createOrder(inventoryId, data));
    }
}
