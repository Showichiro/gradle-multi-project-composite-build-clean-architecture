package com.example.usecase.order.dto;

public record TestOrderRegistrationData(int quantity, String customerInfo)
    implements IOrderRegistrationData {}
