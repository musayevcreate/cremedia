package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.exception.EntityNotFoundException;
import com.cremedia.cremedia.mapper.LikeMapper;
import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;
import com.cremedia.cremedia.repository.LikeRepository;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.service.LikeService;
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
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
    private final PostRepository postRepository;
    private final LikeMapper likeMapper;
    private final ExtractorHelper extractorHelper;

    @Override
    public void likePost(LikeRequestDto likeRequestDto, HttpServletRequest request) {
        log.info("create method is started.");
        likeRequestDto.setUserId(Long.valueOf(extractorHelper.extractUsername(request)));
        var post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + likeRequestDto.getPostId()));

        var like = likeMapper.toEntity(likeRequestDto);
        like.setPost(post);
        like.setLikeDate(LocalDateTime.now());

        likeRepository.save(like);

        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);

        log.info("Post liked successfully by user: {}, post: {}", likeRequestDto.getUserId(), post.getId());
    }

    @Override
    public void unlikePost(Long postId) {
        log.info("unlike : {}", postId);
        likeRepository.deleteById(postId);
    }


    @Override
    public List<LikeResponseDto> getLikesForPost(Long postId) {
        List<Like> likes = likeRepository.findByPostId(postId);
        return likes.stream()
                .map(likeMapper::toDto)
                .collect(Collectors.toList());
    }
}
