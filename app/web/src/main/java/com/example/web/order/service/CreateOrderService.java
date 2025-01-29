package com.example.web.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.inventory.exception.InventoryIrreducibleException;
import com.example.domain.inventory.exception.InventoryNotFoundException;
import com.example.domain.order.model.Order;
import com.example.usecase.order.CreateOrderUseCase;
import com.example.usecase.order.dto.IOrderRegistrationData;

import jakarta.transaction.Transactional;

@Service
public class CreateOrderService {
    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Transactional
    public Order createOrder(Long inventoryId, IOrderRegistrationData data)
            throws InventoryNotFoundException, InventoryIrreducibleException {
        return createOrderUseCase.createOrder(inventoryId, data);
    }
}