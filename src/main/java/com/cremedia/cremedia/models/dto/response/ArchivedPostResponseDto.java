package com.cremedia.cremedia.models.dto.response;

import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import lombok.Data;

@Data
public class ArchivedPostResponseDto {
    private Long id;
    private Post postId;
    private User userId;

}
