package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.Like;

public interface LikeService {
    Like likePost(Long postId, Long userId);
    void unlikePost(Long postId);
    void likeReply(Long replyId, Long userId);
    void unlikeReply(Long replyId, Long userId);

}
