package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.exception.EntityNotFoundException;
import com.cremedia.cremedia.mapper.FollowerMapper;
import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.models.entity.Follower;
import com.cremedia.cremedia.repository.FollowerRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.FollowerService;
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowerServiceImpl implements FollowerService {

    private static final Logger log = LoggerFactory.getLogger(FollowerServiceImpl.class);
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final FollowerMapper followerMapper = FollowerMapper.INSTANCE;
    private final ExtractorHelper extractorHelper;

    @Override
    @Transactional //FIXME ..
    public FollowerResponseDto follow(FollowerRequestDto requestDto, HttpServletRequest request) {
        log.info("Follow method is started.");
        String extractedUsername = extractorHelper.extractUsername(request);
        var followerUser = userRepository.findUserByUsername(extractedUsername)
                .orElseThrow(() -> new EntityNotFoundException("Invalid follower username"));

        requestDto.setFollowerId(followerUser.getId());

        var followingUser = userRepository.findById(requestDto.getFollowingId())
                .orElseThrow(() -> new EntityNotFoundException("Invalid following ID"));

        boolean alreadyFollowing = followerRepository.existsByFollowerAndFollowing(followerUser, followingUser);
        if (alreadyFollowing) {
            throw new EntityNotFoundException("Already following this user");
        }

        Follower follower = new Follower(); //FIXME MAPPER
        follower.setFollower(followerUser);
        follower.setFollowing(followingUser);
        follower.setCreatedAt(LocalDateTime.now());

        follower = followerRepository.save(follower);

        followerUser.setFollowingsCount(followerUser.getFollowingsCount() + 1);
        followingUser.setFollowersCount(followingUser.getFollowersCount() + 1);

        userRepository.save(followerUser);
        userRepository.save(followingUser);

        log.info("Follow method is finished.");
        return followerMapper.toDto(follower);

    }


    @Override
    public void unfollow(FollowerRequestDto requestDto, HttpServletRequest request) {
        log.info("Unfollow method is started.");
        String extractedUsername = extractorHelper.extractUsername(request);
        var followerUser = userRepository.findUserByUsername(extractedUsername)
                .orElseThrow(() -> new EntityNotFoundException("Invalid follower username"));

        requestDto.setFollowerId(followerUser.getId());

        var followingUser = userRepository.findById(requestDto.getFollowingId())
                .orElseThrow(() -> new EntityNotFoundException("Invalid following ID"));

        Follower follower = followerRepository.findByFollowerAndFollowing(followerUser, followingUser)
                .orElseThrow(() -> new EntityNotFoundException("Follow relationship does not exist"));

        followerRepository.delete(follower);

        followerUser.setFollowingsCount(followerUser.getFollowingsCount() - 1);
        followingUser.setFollowersCount(followingUser.getFollowersCount() - 1);

        userRepository.save(followerUser);
        userRepository.save(followingUser);
        log.info("Unfollow method is finished.");
    }

    @Override
    public List<FollowerResponseDto> getFollowers(Long userId) {
        log.info("getFollowers method.");
        return followerRepository.findByFollowingId(userId).stream()
                .map(followerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowerResponseDto> getFollowing(Long userId) {
        log.info("getFollowing method.");
        return followerRepository.findByFollowerId(userId).stream()
                .map(followerMapper::toDto)
                .collect(Collectors.toList());
    }
}
