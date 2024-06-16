package com.cremedia.cremedia.models.dto.request;

import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import lombok.Data;

@Data
public class ReplyRequestDto {
    private Long userId;
    private Long postId;
    private String content;
}
