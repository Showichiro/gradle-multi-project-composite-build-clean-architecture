package com.example.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web.entity.OrderSchema;

public interface OrderRepository extends JpaRepository<OrderSchema, Long> {

}
