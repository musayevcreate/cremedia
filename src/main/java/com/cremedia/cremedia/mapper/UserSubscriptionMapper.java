package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.UserSubscriptionRequestDto;
import com.cremedia.cremedia.models.dto.response.UserSubscriptionResponseDto;
import com.cremedia.cremedia.models.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSubscriptionMapper {
    User toEntity(UserSubscriptionRequestDto dto);
    UserSubscriptionResponseDto toDto(User user);
    List<UserSubscriptionResponseDto> toDtoList(List<User> users);
}