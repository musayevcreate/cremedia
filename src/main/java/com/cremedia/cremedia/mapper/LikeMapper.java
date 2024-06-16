package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class LikeMapper {
    public abstract LikeResponseDto toResponseDto(Like like);
}
