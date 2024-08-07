package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.exception.EntityNotFoundException;
import com.cremedia.cremedia.mapper.UserMapper;
import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.mapper.PostMapper;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.HashtagService;
import com.cremedia.cremedia.service.PostService;
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final HashtagService hashtagService;
    private final ExtractorHelper extractorHelper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public PostResponseDto create(PostRequestDto requestDto, HttpServletRequest request) {
        log.info("create method is started.");
        var user = userRepository.findUserByUsername(extractorHelper.extractUsername(request)).orElseThrow();
        requestDto.setUserId(user.getId());
        var post = postMapper.toEntity(requestDto);

        Set<Hashtag> hashtags = hashtagService.extractHashtags(requestDto.getContent());
        post.setHashtags(hashtags);
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        log.info("create method is finished.");
        return postMapper.toDto(post);
    }


    @Override
    public PostResponseDto getById(Long id ) {
        log.info("getById method is started.");
        var post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        log.info("getById method is finished.");
        return postMapper.toDto(post);
    }

    @Override
    public List<PostResponseDto> getAll() {
        log.info("getAll method is started.");
        List<Post> posts = postRepository.findAll();
        log.info("All posts retrieved: {}", posts);
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<PostResponseDto> getByUserId(Long userId) {
        log.info("getByUserId method is started.");
        List<Post> posts = postRepository.findByUserId(userId);
        log.info("Posts retrieved by userId: {}", posts);
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        log.info("update method is started.");
        var existingPost = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        var updatedPost = postMapper.toEntity(requestDto);
        updatedPost.setId(existingPost.getId());
        var savedPost = postRepository.save(updatedPost);
        log.info("update method is finished.");
        return postMapper.toDto(savedPost);
    }

    @Override
    public void delete(Long id) {
        log.info("delete method is started.");
        postRepository.deleteById(id);
        log.info("Post deleted with id: {}", id);
    }
}
