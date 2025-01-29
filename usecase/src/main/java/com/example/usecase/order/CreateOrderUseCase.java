package com.example.usecase.order;

import com.example.domain.inventory.exception.InventoryIrreducibleException;
import com.example.domain.inventory.exception.InventoryNotFoundException;
import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;
import com.example.usecase.order.dto.IOrderRegistrationData;

public class CreateOrderUseCase {
    private final IOrderGateway orderGateway;
    private final IInventoryGateway inventoryGateway;

    public CreateOrderUseCase(IOrderGateway orderGateway, IInventoryGateway inventoryGateway) {
        this.orderGateway = orderGateway;
        this.inventoryGateway = inventoryGateway;
    }

    public Order createOrder(Long inventoryId, IOrderRegistrationData data)
            throws InventoryNotFoundException, InventoryIrreducibleException {
        Inventory inventory = inventoryGateway.findById(inventoryId)
                // 在庫がない時点でエラー
                .orElseThrow(() -> new InventoryNotFoundException(inventoryId));
        // 在庫を減らすことができない場合、エラー終了.
        if (!inventory.reducible(data.quantity())) {
            throw new InventoryIrreducibleException();
        }
        // 在庫を減らす.
        Inventory reduced = inventory.reduce(data.quantity());
        Inventory updated = inventoryGateway.update(reduced);
        // 注文を登録する.
        Order order = new Order(null, data.quantity(), data.customerInfo(), updated.getId());
        return orderGateway.create(order);
    }
}
