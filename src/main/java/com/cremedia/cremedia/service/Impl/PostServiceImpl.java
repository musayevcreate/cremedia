package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.mapper.PostMapper;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.HashtagRepository;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

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

        Set<Hashtag> hashtags = extractHashtags(requestDto.getContent());
        post.setHashtags(hashtags);

        Post savedPost = postRepository.save(post);

        return postMapper.toDto(savedPost);
    }

    private Set<Hashtag> extractHashtags(String content) {
        Set<Hashtag> hashtags = new HashSet<>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String text = matcher.group().substring(1).toLowerCase(); // Remove '#' and convert to lowercase
            Hashtag hashtag = hashtagRepository.findByText(text)
                    .orElseGet(() -> {
                        Hashtag newHashtag = new Hashtag();
                        newHashtag.setText(text);
                        return hashtagRepository.save(newHashtag);
                    });
            hashtags.add(hashtag);
        }
        return hashtags;
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
