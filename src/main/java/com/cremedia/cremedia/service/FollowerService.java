package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.Follower;

import java.util.List;

public interface FollowerService {
    Follower follow(Long followerId, Long followingId);
    void unfollow(Long followerId, Long followingId);
    List<UserResponseDto> getFollowers(Long userId);
    List<UserResponseDto> getFollowing(Long userId);

}
