package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.entity.Like;

public interface LikeService {
    void     likePost(LikeRequestDto likeRequestDto);
    void unlikePost(Long postId);
    void likeReply(Long replyId, Long userId);
    void unlikeReply(Long replyId, Long userId);

}
