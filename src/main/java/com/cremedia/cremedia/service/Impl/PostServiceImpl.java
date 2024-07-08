package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.mapper.PostMapper;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.PostService;
import com.cremedia.cremedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponseDto create(PostRequestDto requestDto) {
        Post post = postMapper.toEntity(requestDto);

        if (requestDto.getUserId() != null) {
            User user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            post.setUser(user);
        } else {
            post.setUser(null);
        }
        Post savedPost = postRepository.save(post);

        return postMapper.toDto(savedPost);
    }

    @Override
    public PostResponseDto getById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }
        return postMapper.toDto(post);
    }

    @Override
    public List<PostResponseDto> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<PostResponseDto> getByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost == null) {
            return null;
        }
        Post updatedPost = postMapper.toEntity(requestDto);
        updatedPost.setId(existingPost.getId());
        Post savedPost = postRepository.save(updatedPost);
        return postMapper.toDto(savedPost);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
