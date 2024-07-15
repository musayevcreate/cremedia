package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;

import java.util.List;

public interface LikeService {
    void     likePost(LikeRequestDto likeRequestDto);
    void unlikePost(Long postId);
    void likeReply(Long replyId, Long userId);
    void unlikeReply(Long replyId, Long userId);
    List<LikeResponseDto> getLikesForPost(Long postId);
}
