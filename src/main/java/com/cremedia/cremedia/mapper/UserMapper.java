package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.auth.UserRegisterDTO;
import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserResponseDto toResponseDto(User user);
    public abstract User toEntity(UserRequestDto userRequestDto);
    public abstract User toRegisterEntity(UserRegisterDTO userRegisterDTO);

    public abstract void updateFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}

