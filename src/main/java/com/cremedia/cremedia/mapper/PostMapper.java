package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    public abstract PostResponseDto toResponse(Post post);

    public abstract Post toEntity(PostRequestDto postRequestDto);

    public abstract void updateFromDto(PostRequestDto postRequestDto, @MappingTarget Post post);


}


