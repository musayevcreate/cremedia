package com.cremedia.cremedia.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class FollowerRequestDto {
    @Schema(hidden = true)
    private Long followerId;
    private Long followingId;
}

