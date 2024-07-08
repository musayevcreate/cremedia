package com.cremedia.cremedia.models.dto.response;

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
    private Long comments = 0L;
    private String mentions;
    private String status;
    private String media;
    private LocalDateTime createdAt;
    private boolean isArchived;
    private String visibility;
    private List<Long> hashtagIds;
    private Set<Long> emotionIds;
    private List<Long> replyIds;
    private Long replyToId;
}
