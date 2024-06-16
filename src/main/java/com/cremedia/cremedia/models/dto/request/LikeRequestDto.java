package com.cremedia.cremedia.models.dto.request;

import lombok.Data;

@Data
public class LikeRequestDto {
    private Long postId;
    private Long userId;

}
