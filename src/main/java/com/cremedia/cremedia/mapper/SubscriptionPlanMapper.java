package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.SubscriptionPlanRequestDto;
import com.cremedia.cremedia.models.dto.response.SubscriptionPlanResponseDto;
import com.cremedia.cremedia.models.entity.SubscriptionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper {
    SubscriptionPlan toEntity(SubscriptionPlanRequestDto dto);
    SubscriptionPlanResponseDto toDto(SubscriptionPlan entity);
    List<SubscriptionPlanResponseDto> toDtoList(List<SubscriptionPlan> entities);
}
