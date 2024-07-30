package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.exception.EntityNotFoundException;
import com.cremedia.cremedia.mapper.SubscriptionPlanMapper;
import com.cremedia.cremedia.models.dto.request.SubscriptionPlanRequestDto;
import com.cremedia.cremedia.models.dto.response.SubscriptionPlanResponseDto;
import com.cremedia.cremedia.models.entity.Subscription;
import com.cremedia.cremedia.models.entity.SubscriptionPlan;
import com.cremedia.cremedia.repository.SubscriptionPlanRepository;
import com.cremedia.cremedia.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionPlanMapper subscriptionPlanMapper;

    @Override
    public SubscriptionPlanResponseDto createSubscriptionPlan(SubscriptionPlanRequestDto dto) {
        log.info("Creating a new subscription plan: {}", dto.getPlanType());
        SubscriptionPlan subscriptionPlan = subscriptionPlanMapper.toEntity(dto);
        SubscriptionPlan savedPlan = subscriptionPlanRepository.save(subscriptionPlan);
        return subscriptionPlanMapper.toDto(savedPlan);
    }

    @Override
    public List<SubscriptionPlanResponseDto> getAllSubscriptionPlans() {
        List<SubscriptionPlan> plans = subscriptionPlanRepository.findAll();
        return subscriptionPlanMapper.toDtoList(plans);
    }

    @Override
    public SubscriptionPlanResponseDto getSubscriptionPlanById(Long id) {
        SubscriptionPlan plan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan not found"));
        return subscriptionPlanMapper.toDto(plan);
    }

    @Override
    public SubscriptionPlanResponseDto updateSubscriptionPlan(Long id, SubscriptionPlanRequestDto dto) {
        log.info("Updating subscription plan with id: {}", id);
        SubscriptionPlan existingPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan not found"));
        existingPlan.setPlanType(dto.getPlanType());
        existingPlan.setPrice(dto.getPlanType().getPrice());
        existingPlan.setDescription(dto.getDescription());
        SubscriptionPlan updatedPlan = subscriptionPlanRepository.save(existingPlan);
        return subscriptionPlanMapper.toDto(updatedPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long id) {
        log.info("Deleting subscription plan with id: {}", id);
        subscriptionPlanRepository.deleteById(id);
    }


}
