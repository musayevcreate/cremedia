package com.cremedia.cremedia.models.dto.response;


import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import lombok.Data;

@Data
public class ReplyResponseDto {
    private Long id;
    private Long postId;
    private Long userId;
    private String content;
}
