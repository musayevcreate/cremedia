package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.mapper.PostMapper;
import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostResponseDto create(PostRequestDto postRequestDto) {
        Post post = postMapper.toEntity(postRequestDto);
        post.setUser(userRepository.findById(postRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        if (postRequestDto.getReplyTo() != null) {
            post.setReplyTo(postRepository.findById(postRequestDto.getReplyTo().getId())
                    .orElseThrow(() -> new RuntimeException("Reply to post not found")));
        }
        postRepository.save(post);
        log.info("Post created: {}", post);
        return postMapper.toResponse(post);
    }

    @Override
    public PostResponseDto getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        log.info("Post retrieved: {}", post);
        return postMapper.toResponse(post);
    }

    @Override
    public List<PostResponseDto> getAll() {
        List<Post> posts = postRepository.findAll();
        log.info("All posts retrieved: {}", posts);
        return posts.stream()
                .map(postMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postMapper.updateFromDto(postRequestDto, post);
        post.setUser(userRepository.findById(postRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        if (postRequestDto.getReplyTo() != null) {
            post.setReplyTo(postRepository.findById(postRequestDto.getReplyTo().getId())
                    .orElseThrow(() -> new RuntimeException("Reply to post not found")));
        }
        postRepository.save(post);
        log.info("Post updated: {}", post);
        return postMapper.toResponse(post);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
        log.info("Post deleted with id: {}", id);
    }

    @Override
    public List<PostResponseDto> getByUser(Long userId) {
        return List.of();
    }
}
