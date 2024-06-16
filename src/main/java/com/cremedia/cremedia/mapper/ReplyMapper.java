package com.cremedia.cremedia.mapper;


import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.Reply;
import com.cremedia.cremedia.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ReplyMapper {

    public abstract ReplyResponseDto toResponseDto(Reply reply);
    public abstract Reply toEntity(ReplyRequestDto replyRequestDto);

    public abstract void updateFromDto(ReplyRequestDto replyRequestDto, @MappingTarget Reply reply);
}
