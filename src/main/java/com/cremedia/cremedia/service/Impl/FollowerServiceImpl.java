package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.Follower;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.FollowerRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Override
    public Follower follow(Long followerId, Long followeeId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found with id: " + followerId));

        User following = userRepository.findById(followeeId)
                .orElseThrow(() -> new RuntimeException("Following not found with id: " + followeeId));

        Follower existingFollower = followerRepository.findByFollowerAndFollowing(follower, following);
        if (existingFollower != null) {
            throw new RuntimeException("User already followed");
        }

        Follower newFollower = new Follower();
        newFollower.setFollower(follower);
        newFollower.setFollowing(following);
        newFollower.setCreatedAt(LocalDateTime.now());
        return followerRepository.save(newFollower);
    }

    @Override
    public void unfollow(Long followerId, Long followeeId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found with id: " + followerId));

        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new RuntimeException("Following not found with id: " + followeeId));

        Follower existingFollower = followerRepository.findByFollowerAndFollowing(follower, followee);
        if (existingFollower == null) {
            throw new RuntimeException("User is not followed");
        }

        followerRepository.delete(existingFollower);
    }

    @Override
    public List<UserResponseDto> getFollowers(Long userId) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getFollowing(Long userId) {
        return List.of();
    }


}
