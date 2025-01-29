package com.example.web.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.order.exception.OrderNotFoundException;
import com.example.usecase.order.GetOrderUseCase;
import com.example.web.order.dto.OrderPublicData;

@RestController
public class GetOrderController {
    @Autowired
    private GetOrderUseCase getOrderUseCase;

    @GetMapping("orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderPublicData getOrder(@PathVariable Long id) throws OrderNotFoundException {
        return OrderPublicData.fromOrder(getOrderUseCase.getOrder(id));
    }
}
