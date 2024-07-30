package com.cremedia.cremedia.models.dto.request;


import com.cremedia.cremedia.enums.SubscriptionPlanType;
import lombok.Data;

@Data
public class SubscriptionPlanRequestDto {
    private SubscriptionPlanType planType;
    private String description;

}
