package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.models.entity.Follower;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface FollowerService {
    FollowerResponseDto follow(FollowerRequestDto dto, HttpServletRequest request);
    void unfollow(FollowerRequestDto requestDto, HttpServletRequest request);

    List<FollowerResponseDto> getFollowers(Long userId);
    List<FollowerResponseDto> getFollowing(Long userId);

}
