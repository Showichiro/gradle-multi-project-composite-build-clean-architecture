package com.example.domain.inventory.gateway;

import java.util.List;

import com.example.domain.core.IGateway;
import com.example.domain.inventory.model.Inventory;

public interface IInventoryGateway extends IGateway<Inventory, Long> {
  List<Inventory> findAll(int size, int page);
}
