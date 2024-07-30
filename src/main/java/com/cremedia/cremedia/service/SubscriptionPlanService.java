package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.SubscriptionPlanRequestDto;
import com.cremedia.cremedia.models.dto.response.SubscriptionPlanResponseDto;

import java.util.List;

public interface SubscriptionPlanService {
    SubscriptionPlanResponseDto createSubscriptionPlan(SubscriptionPlanRequestDto dto);
    List<SubscriptionPlanResponseDto> getAllSubscriptionPlans();
    SubscriptionPlanResponseDto getSubscriptionPlanById(Long id);
    SubscriptionPlanResponseDto updateSubscriptionPlan(Long id, SubscriptionPlanRequestDto dto);
    void deleteSubscriptionPlan(Long id);
}
