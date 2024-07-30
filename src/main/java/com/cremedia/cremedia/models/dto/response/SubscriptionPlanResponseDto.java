package com.cremedia.cremedia.models.dto.response;

import com.cremedia.cremedia.enums.SubscriptionPlanType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubscriptionPlanResponseDto {
    private Long id;
    private SubscriptionPlanType planType;
    private String description;
    private BigDecimal price;
}
