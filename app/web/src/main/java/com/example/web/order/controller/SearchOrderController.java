package com.example.web.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.usecase.order.SearchOrderUseCase;
import com.example.web.order.dto.OrderPublicData;
import com.example.web.order.dto.OrderSearchData;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
public class SearchOrderController {
    @Autowired
    private SearchOrderUseCase searchOrderUseCase;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderPublicData> searchOrderPublicData(
            @Valid @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(name = "page", required = false, defaultValue = "0") @Min(0) int page) {
        OrderSearchData data = new OrderSearchData(size, page);
        return searchOrderUseCase.searchOrders(data)
                .stream()
                .map(OrderPublicData::fromOrder)
                .toList();
    }
}
