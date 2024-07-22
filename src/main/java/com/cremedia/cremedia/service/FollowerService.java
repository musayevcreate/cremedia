package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.models.entity.Follower;

import java.util.List;

public interface FollowerService {
    FollowerResponseDto follow(FollowerRequestDto dto);
    void unfollow(Long id);
    List<FollowerResponseDto> getFollowers(Long userId);
    List<FollowerResponseDto> getFollowing(Long userId);

}
