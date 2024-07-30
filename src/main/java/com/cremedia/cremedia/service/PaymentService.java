package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.User;

import java.math.BigDecimal;

public interface PaymentService {
    boolean processPayment(User user, BigDecimal amount, String cardNumber, String expiryDate, String cvv);
}
