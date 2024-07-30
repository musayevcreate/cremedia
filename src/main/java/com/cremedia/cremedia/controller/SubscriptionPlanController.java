package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.SubscriptionPlanRequestDto;
import com.cremedia.cremedia.models.dto.response.SubscriptionPlanResponseDto;
import com.cremedia.cremedia.service.SubscriptionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/subscription-plans")
@Slf4j
@RequiredArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @Operation(summary = "Create a new Subscription Plan")
    @PostMapping
    public SubscriptionPlanResponseDto createSubscriptionPlan(@RequestBody SubscriptionPlanRequestDto dto) {
        log.info("Received request to create a subscription plan");
        return subscriptionPlanService.createSubscriptionPlan(dto);
    }

    @Operation(summary = "Get all Subscription Plans")
    @GetMapping
    public List<SubscriptionPlanResponseDto> getAllSubscriptionPlans() {
        log.info("Received request to get all subscription plans");
        return subscriptionPlanService.getAllSubscriptionPlans();
    }

    @Operation(summary = "Get Subscription Plan by ID")
    @GetMapping("/{id}")
    public SubscriptionPlanResponseDto getSubscriptionPlanById(@PathVariable Long id) {
        log.info("Received request to get subscription plan with id: {}", id);
        return subscriptionPlanService.getSubscriptionPlanById(id);
    }

    @Operation(summary = "Update Subscription Plan")
    @PutMapping("/{id}")
    public SubscriptionPlanResponseDto updateSubscriptionPlan(@PathVariable Long id, @RequestBody SubscriptionPlanRequestDto dto) {
        log.info("Received request to update subscription plan with id: {}", id);
        return subscriptionPlanService.updateSubscriptionPlan(id, dto);
    }

    @Operation(summary = "Delete Subscription Plan by ID")
    @DeleteMapping("/{id}")
    public void deleteSubscriptionPlan(@PathVariable Long id) {
        log.info("Received request to delete subscription plan with id: {}", id);
        subscriptionPlanService.deleteSubscriptionPlan(id);
    }
}
