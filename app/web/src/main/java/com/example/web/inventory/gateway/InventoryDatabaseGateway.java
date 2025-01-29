package com.example.web.inventory.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.domain.inventory.gateway.IInventoryGateway;
import com.example.domain.inventory.model.Inventory;
import com.example.web.mapper.InventoryMapper;
import com.example.web.repository.InventoryRepository;

@Component
public class InventoryDatabaseGateway implements IInventoryGateway {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory create(Inventory product) {
        return InventoryMapper.toInventory(inventoryRepository.save(InventoryMapper.fromInventory(product)));
    }

    @Override
    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll().stream().map(InventoryMapper::toInventory).toList();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id).map(InventoryMapper::toInventory);
    }

    @Override
    public Inventory update(Inventory product) {
        return InventoryMapper.toInventory(inventoryRepository.save(InventoryMapper.fromInventory(product)));
    }

    @Override
    public List<Inventory> findAll(int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return inventoryRepository.findAll(pageRequest).getContent()
                .stream()
                .map(InventoryMapper::toInventory)
                .toList();
    }
}