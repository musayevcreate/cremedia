package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.mapper.FollowerMapper;
import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.models.entity.Follower;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.FollowerRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final FollowerMapper followerMapper = FollowerMapper.INSTANCE;

    @Autowired
    public FollowerServiceImpl(FollowerRepository followerRepository, UserRepository userRepository) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FollowerResponseDto follow(FollowerRequestDto dto) {
        User followerUser = userRepository.findById(dto.getFollowerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid follower ID"));
        User followingUser = userRepository.findById(dto.getFollowingId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid following ID"));

        Follower follower = new Follower();
        follower.setFollower(followerUser);
        follower.setFollowing(followingUser);
        follower.setCreatedAt(LocalDateTime.now());

        Follower savedFollower = followerRepository.save(follower);

        followerUser.setFollowingsCount(followerUser.getFollowingsCount() + 1);
        followingUser.setFollowersCount(followingUser.getFollowersCount() + 1);

        userRepository.save(followerUser);
        userRepository.save(followingUser);

        return followerMapper.toDto(savedFollower);
    }

    @Override
    public void unfollow(Long id) {
        Follower follower = followerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid follower ID"));

        User followerUser = follower.getFollower();
        User followingUser = follower.getFollowing();

        followerRepository.deleteById(id);

        followerUser.setFollowingsCount(followerUser.getFollowingsCount() - 1);
        followingUser.setFollowersCount(followingUser.getFollowersCount() - 1);

        userRepository.save(followerUser);
        userRepository.save(followingUser);
    }

    @Override
    public List<FollowerResponseDto> getFollowers(Long userId) {
        return followerRepository.findByFollowingId(userId).stream()
                .map(followerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowerResponseDto> getFollowing(Long userId) {
        return followerRepository.findByFollowerId(userId).stream()
                .map(followerMapper::toDto)
                .collect(Collectors.toList());
    }
}
