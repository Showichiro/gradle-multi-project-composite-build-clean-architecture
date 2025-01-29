package com.example.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web.entity.InventorySchema;

public interface InventoryRepository extends JpaRepository<InventorySchema, Long> {
    boolean existsByName(String name);
}