package com.example.web.order.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.domain.order.gateway.IOrderGateway;
import com.example.domain.order.model.Order;
import com.example.web.entity.InventorySchema;
import com.example.web.entity.OrderSchema;
import com.example.web.mapper.OrderMapper;
import com.example.web.repository.InventoryRepository;
import com.example.web.repository.OrderRepository;

@Component
public class OrderDatabaseGateway implements IOrderGateway {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Order create(Order entity) {
        InventorySchema inventorySchema = inventoryRepository.findById(entity.getInventoryId()).orElseThrow();
        OrderSchema orderSchema = OrderMapper.fromOrder(entity);
        orderSchema.setInventorySchema(inventorySchema);
        return OrderMapper.toOrder(orderRepository.save(orderSchema));
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Order update(Order product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> findAll(int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return orderRepository.findAll(pageRequest).getContent()
                .stream()
                .map(OrderMapper::toOrder)
                .toList();
    }
}
