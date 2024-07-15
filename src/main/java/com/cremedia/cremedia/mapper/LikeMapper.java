package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class LikeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likeDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    public abstract Like toEntity(LikeRequestDto likeRequestDto);

    @Mapping(source = "id", target = "likeId")
    @Mapping(source = "user.id", target = "userId")
    public abstract LikeResponseDto toDto(Like like);
}
