package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface LikeService {
    void     likePost(LikeRequestDto likeRequestDto, HttpServletRequest request);
    void unlikePost(Long postId);
  List<LikeResponseDto> getLikesForPost(Long postId);
}
