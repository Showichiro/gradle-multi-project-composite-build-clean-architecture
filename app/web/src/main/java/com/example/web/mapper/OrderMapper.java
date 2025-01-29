package com.example.web.mapper;

import com.example.domain.order.model.Order;
import com.example.web.entity.OrderSchema;

public class OrderMapper {
    private OrderMapper() {
    }

    public static OrderSchema fromOrder(Order order) {
        return new OrderSchema(order);
    }

    public static Order toOrder(OrderSchema orderSchema) {
        return new Order(orderSchema.getId(),
                orderSchema.getQuantity(),
                orderSchema.getCustomerInfo(),
                orderSchema.getInventorySchema().getId(),
                orderSchema.getInventorySchema().getName());
    }
}