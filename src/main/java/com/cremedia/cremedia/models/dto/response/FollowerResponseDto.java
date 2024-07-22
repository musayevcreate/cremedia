package com.cremedia.cremedia.models.dto.response;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FollowerResponseDto {
    private Long id;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}
