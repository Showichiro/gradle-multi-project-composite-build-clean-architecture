package com.example.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.usecase.inventory.CreateInventoryUseCase;
import com.example.usecase.inventory.DeleteInventoryUseCase;
import com.example.usecase.inventory.GetInventoryUseCase;
import com.example.usecase.inventory.SearchInventoryUseCase;
import com.example.usecase.order.CreateOrderUseCase;
import com.example.usecase.order.DeleteOrderUseCase;
import com.example.usecase.order.GetOrderUseCase;
import com.example.usecase.order.SearchOrderUseCase;
import com.example.web.inventory.gateway.InventoryDatabaseGateway;
import com.example.web.order.gateway.OrderDatabaseGateway;

@Configuration
public class UseCaseConfig {

    // Inventory

    @Bean
    CreateInventoryUseCase createInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new CreateInventoryUseCase(inventoryDatabaseGateway);
    }

    @Bean
    GetInventoryUseCase getInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new GetInventoryUseCase(inventoryDatabaseGateway);
    }

    @Bean
    SearchInventoryUseCase searchInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new SearchInventoryUseCase(inventoryDatabaseGateway);
    }

    @Bean
    DeleteInventoryUseCase deleteInventoryUseCase(InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new DeleteInventoryUseCase(inventoryDatabaseGateway);
    }

    // Order

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderDatabaseGateway orderDatabaseGateway,
            InventoryDatabaseGateway inventoryDatabaseGateway) {
        return new CreateOrderUseCase(orderDatabaseGateway, inventoryDatabaseGateway);
    }

    @Bean
    GetOrderUseCase getOrderUseCase(OrderDatabaseGateway orderDatabaseGateway) {
        return new GetOrderUseCase(orderDatabaseGateway);
    }

    @Bean
    SearchOrderUseCase searchOrderUseCase(OrderDatabaseGateway orderDatabaseGateway) {
        return new SearchOrderUseCase(orderDatabaseGateway);
    }

    @Bean
    DeleteOrderUseCase deleteOrderUseCase(OrderDatabaseGateway orderDatabaseGateway) {
        return new DeleteOrderUseCase(orderDatabaseGateway);
    }
}
