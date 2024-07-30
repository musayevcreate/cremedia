package com.cremedia.cremedia.models.dto.request;

import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReplyRequestDto {
    @Schema(hidden = true)
    private Long userId;
    private Long postId;
    private String content;
}
