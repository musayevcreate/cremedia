package com.cremedia.cremedia.models.dto.request;

import lombok.Data;
@Data
public class FollowerRequestDto {
    private Long followerId;
    private Long followingId;
}

