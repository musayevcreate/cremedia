package com.cremedia.cremedia.models.dto.response;

import com.cremedia.cremedia.enums.SubscriptionPlanType;
import lombok.Data;

@Data
public class UserSubscriptionResponseDto {
    private String username;
    private SubscriptionPlanType subscriptionPlan;
    private boolean hasBlueTick;
    private String tagName;

}
