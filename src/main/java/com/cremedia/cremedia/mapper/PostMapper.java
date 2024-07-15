package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.Reply;
import com.cremedia.cremedia.service.HashtagService;
import com.cremedia.cremedia.service.UserService;
import org.mapstruct.*;
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

    @Mapping(target = "hashtags", qualifiedByName = "mapToHashtagTexts")
    @Mapping(target = "emotionIds", ignore = true)
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "replies", target = "replies")
    public abstract PostResponseDto toDto(Post post);

    @Named("mapToHashtagTexts")
    protected List<String> mapToHashtagTexts(Set<Hashtag> hashtags) {
        return hashtags.stream()
                .map(Hashtag::getText)
                .collect(Collectors.toList());
    }

    @Mapping(source = "userId", target = "user.id")
    public abstract Post toEntity(PostRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "replyTo", ignore = true)
    public abstract void updateFromDto(PostRequestDto dto, @MappingTarget Post post);

    public abstract List<ReplyResponseDto> mapReplies(List<Reply> replies);
}
