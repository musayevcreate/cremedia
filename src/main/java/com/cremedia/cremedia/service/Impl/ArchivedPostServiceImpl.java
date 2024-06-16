package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.dto.response.ArchivedPostResponseDto;
import com.cremedia.cremedia.models.entity.ArchivedPost;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.ArchivedPostRepository;
import com.cremedia.cremedia.service.ArchivedPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArchivedPostServiceImpl implements ArchivedPostService {

    private final ArchivedPostRepository archivedPostRepository;

    @Override
    public ArchivedPostResponseDto archive(Post postId, User userId) {
        ArchivedPost archivedPost = new ArchivedPost();
//        archivedPost.setPostId(postId);
//        archivedPost.setUserId(userId);
        archivedPost = archivedPostRepository.save(archivedPost);
        log.info("Post archived successfully: {}", archivedPost);
        return mapToResponseDto(archivedPost);
    }

    @Override
    public ArchivedPostResponseDto unarchive(Post postId, User userId){
        ArchivedPost archivedPost = archivedPostRepository.findByPostIdAndUserId(postId, userId);
        if (archivedPost != null) {
            archivedPostRepository.delete(archivedPost);
            log.info("Post unarchived successfully: {}", archivedPost);
            return mapToResponseDto(archivedPost);
        } else {
            log.error("Post not found in archived posts: {}", postId);
            return null;
        }
    }

    @Override
    public List<ArchivedPostResponseDto> getAll(User userId) {
        List<ArchivedPost> archivedPosts = archivedPostRepository.findById(userId);
        return archivedPosts.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private ArchivedPostResponseDto mapToResponseDto(ArchivedPost archivedPost) {
        ArchivedPostResponseDto responseDto = new ArchivedPostResponseDto();
        responseDto.setId(archivedPost.getId());
//        responseDto.setPostId(archivedPost.getPostId());
//        responseDto.setUserId(archivedPost.getUserId());
        return responseDto;
    }
}
