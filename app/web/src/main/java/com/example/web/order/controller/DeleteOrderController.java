package com.example.web.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.usecase.order.DeleteOrderUseCase;

@RestController
public class DeleteOrderController {
    @Autowired
    private DeleteOrderUseCase deleteOrderUseCase;

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Long id) {
        deleteOrderUseCase.deleteOrder(id);
    }
}
