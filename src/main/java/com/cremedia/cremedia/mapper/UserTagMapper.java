package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.UserTagRequestDto;
import com.cremedia.cremedia.models.dto.response.UserTagResponseDto;
import com.cremedia.cremedia.models.entity.UserTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserTagMapper {
   public abstract UserTag toEntity(UserTagRequestDto dto);

    @Mapping(source = "user.id", target = "userId")
    public abstract UserTagResponseDto toDto(UserTag entity);
}