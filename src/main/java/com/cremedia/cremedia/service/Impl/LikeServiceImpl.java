package com.cremedia.cremedia.service.impl;

import com.cremedia.cremedia.mapper.LikeMapper;
import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.LikeRepository;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeMapper likeMapper;

    @Override
    public void likePost(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + likeRequestDto.getUserId()));

        Post post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + likeRequestDto.getPostId()));

        Like like = likeMapper.toEntity(likeRequestDto);
        like.setUser(user);
        like.setPost(post);
        like.setLikeDate(LocalDateTime.now());

        likeRepository.save(like);

        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);

        log.info("Post liked successfully by user: {}, post: {}", user.getId(), post.getId());
    }

    @Override
    public void unlikePost(Long postId) {
        log.info("unlike : {}", postId);
        likeRepository.deleteById(postId);
    }

    @Override
    public void likeReply(Long replyId, Long userId) {
        // Implement the like reply logic if needed
    }

    @Override
    public void unlikeReply(Long replyId, Long userId) {
        // Implement the unlike reply logic if needed
    }

    @Override
    public List<LikeResponseDto> getLikesForPost(Long postId) {
        List<Like> likes = likeRepository.findByPostId(postId);
        return likes.stream()
                .map(likeMapper::toDto)
                .collect(Collectors.toList());
    }
}
