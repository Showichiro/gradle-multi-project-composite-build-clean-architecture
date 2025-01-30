package com.example.web.order.dto;

import com.example.usecase.order.dto.IOrderSearchData;

public record OrderSearchData(int size, int page) implements IOrderSearchData {
}
