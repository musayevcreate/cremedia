package com.cremedia.cremedia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum SubscriptionPlanType {
    FREE("Free Plan", BigDecimal.ZERO),
    PREMIUM("Premium Plan", new BigDecimal("20.00"));

    private final String description;
    private final BigDecimal price;

}
