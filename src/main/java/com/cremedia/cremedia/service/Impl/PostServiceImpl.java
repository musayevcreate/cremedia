package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.mapper.PostMapper;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.service.HashtagService;
import com.cremedia.cremedia.service.PostService;
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final HashtagService hashtagService;
    private final ExtractorHelper extractorHelper;


    @Override
    public PostResponseDto create(PostRequestDto requestDto, HttpServletRequest request) {
        requestDto.setUserId(Long.valueOf(extractorHelper.extractUsername(request)));

        Post post = postMapper.toEntity(requestDto);

        Set<Hashtag> hashtags = hashtagService.extractHashtags(requestDto.getContent());
        post.setHashtags(hashtags);

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
