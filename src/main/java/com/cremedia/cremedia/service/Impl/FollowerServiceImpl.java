package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.mapper.FollowerMapper;
import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.models.entity.Follower;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.FollowerRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.FollowerService;
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final FollowerMapper followerMapper = FollowerMapper.INSTANCE;
    private final ExtractorHelper extractorHelper;

    @Override
    public FollowerResponseDto follow(FollowerRequestDto requestDto, HttpServletRequest request) {
        String extractedUsername = extractorHelper.extractUsername(request);
        var followerUser = userRepository.findUserByUsername(extractedUsername)
                .orElseThrow(() -> new IllegalArgumentException("Invalid follower username"));

        requestDto.setFollowerId(followerUser.getId());

        var followingUser = userRepository.findById(requestDto.getFollowingId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid following ID"));

        boolean alreadyFollowing = followerRepository.existsByFollowerAndFollowing(followerUser, followingUser);
        if (alreadyFollowing) {
            throw new IllegalArgumentException("Already following this user");
        }

        Follower follower = new Follower();
        follower.setFollower(followerUser);
        follower.setFollowing(followingUser);
        follower.setCreatedAt(LocalDateTime.now());

        var savedFollower = followerRepository.save(follower);

        followerUser.setFollowingsCount(followerUser.getFollowingsCount() + 1);
        followingUser.setFollowersCount(followingUser.getFollowersCount() + 1);

        userRepository.save(followerUser);
        userRepository.save(followingUser);

        return followerMapper.toDto(savedFollower);
    }


    @Override
    public void unfollow(FollowerRequestDto requestDto, HttpServletRequest request) {
        String extractedUsername = extractorHelper.extractUsername(request);
        var followerUser = userRepository.findUserByUsername(extractedUsername)
                .orElseThrow(() -> new IllegalArgumentException("Invalid follower username"));

        requestDto.setFollowerId(followerUser.getId());

        var followingUser = userRepository.findById(requestDto.getFollowingId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid following ID"));

        Follower follower = followerRepository.findByFollowerAndFollowing(followerUser, followingUser)
                .orElseThrow(() -> new IllegalArgumentException("Follow relationship does not exist"));

        followerRepository.delete(follower);

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
