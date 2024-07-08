package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.service.HashtagService;
import com.cremedia.cremedia.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PostMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    protected HashtagService hashtagService;

    public static PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "hashtagIds", source = "hashtags")
    @Mapping(target = "emotionIds", ignore = true)
    @Mapping(source = "user.id", target = "userId")
    public abstract PostResponseDto toDto(Post post);

    @Mapping(source = "userId", target = "user.id")
    public abstract Post toEntity(PostRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "replyTo", ignore = true)
    public abstract void updateFromDto(PostRequestDto dto, @MappingTarget Post post);

    protected List<Long> mapToIds(Set<Hashtag> hashtags) {
        return hashtags.stream()
                .map(Hashtag::getId)
                .collect(Collectors.toList());
    }
}
