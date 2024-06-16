package com.cremedia.cremedia.service.Impl;

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

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Like likePost(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like.setLikeDate(LocalDateTime.now());
        return like;
    }

    @Override
    public void unlikePost(Long postId) {
        log.info("unlike : {}", postId);
        likeRepository.deleteById(postId);
    }

    @Override
    public void likeReply(Long replyId, Long userId) {

    }

    @Override
    public void unlikeReply(Long replyId, Long userId) {

    }


}
