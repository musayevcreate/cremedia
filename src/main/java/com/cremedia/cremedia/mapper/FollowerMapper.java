package com.cremedia.cremedia.mapper;
import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.models.entity.Follower;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowerMapper {
    FollowerMapper INSTANCE = Mappers.getMapper(FollowerMapper.class);

    Follower toEntity(FollowerRequestDto dto);
    FollowerResponseDto toDto(Follower entity);
}
