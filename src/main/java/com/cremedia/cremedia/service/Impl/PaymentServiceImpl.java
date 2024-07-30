package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private static final String VALID_CARD_NUMBER = "4169738810101111";
    private static final String VALID_EXPIRY_DATE = "10/26";
    private static final String VALID_CVV = "500";

    @Override
    public boolean processPayment(User user, BigDecimal amount, String cardNumber, String expiryDate, String cvv) {
        log.info("Processing payment for user {}", user.getUsername());

        if (VALID_CARD_NUMBER.equals(cardNumber) &&
                VALID_EXPIRY_DATE.equals(expiryDate) &&
                VALID_CVV.equals(cvv)) {
            log.info("Payment of {} for user {} was successful", amount, user.getUsername());
            return true;
        } else {
            log.error("Payment failed for user {}. Invalid card details.", user.getUsername());
            return false;
        }
    }
}
