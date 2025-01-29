package com.example.web.order.dto;

import com.example.usecase.order.dto.IOrderRegistrationData;

public record OrderRegistrationData(
        int quantity,
        String customerInfo) implements IOrderRegistrationData {
}
