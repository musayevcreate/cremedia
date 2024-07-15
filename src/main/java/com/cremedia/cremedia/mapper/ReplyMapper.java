package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReplyMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "post.id", target = "postId")
    ReplyResponseDto toDto(Reply reply);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "postId", target = "post.id")
    Reply toEntity(ReplyRequestDto dto);

    void updateFromDto(ReplyRequestDto dto, @MappingTarget Reply reply);
}
