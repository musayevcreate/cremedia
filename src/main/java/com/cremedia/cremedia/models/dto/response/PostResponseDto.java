package com.cremedia.cremedia.models.dto.response;

import com.cremedia.cremedia.models.entity.Reply;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class PostResponseDto {

    private Long id;
    private Long userId;
    private String content;
    private Long likes = 0L;
    private Long retweets = 0L;
    private String mentions;
    private String status;
    private String media;
    private LocalDateTime createdAt;
    private boolean isArchived;
    private List<String> hashtags;
    private Set<Long> emotionIds;
    private Long replyToId;
    private List<ReplyResponseDto> replies;
}
