package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.mapper.ReplyMapper;
import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.Reply;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.PostRepository;
import com.cremedia.cremedia.repository.ReplyRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyMapper replyMapper;

    @Override
    public ReplyResponseDto create(ReplyRequestDto requestDto) {
        Reply reply = replyMapper.toEntity(requestDto);

        if (requestDto.getUserId() != null) {
            User user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            reply.setUser(user);
        } else {
            throw new IllegalArgumentException("User ID cannot be null for creating a reply.");
        }

        if (requestDto.getPostId() != null) {
            Post post = postRepository.findById(requestDto.getPostId())
                    .orElseThrow(() -> new RuntimeException("Post not found"));
            reply.setPost(post);

            Reply savedReply = replyRepository.save(reply);

            post.getReplies().add(savedReply);
            postRepository.save(post);

            log.info("Reply created successfully for post: {}", post.getId());

            return replyMapper.toDto(savedReply);
        } else {
            throw new IllegalArgumentException("Post ID cannot be null for creating a reply.");
        }
    }



    @Override
    public ReplyResponseDto getById(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        return replyMapper.toDto(reply);
    }

    @Override
    @Transactional
    public ReplyResponseDto update(Long id, ReplyRequestDto replyRequestDto) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        replyMapper.updateFromDto(replyRequestDto, reply);
        replyRepository.save(reply);
        log.info("Reply updated successfully: {}", reply);
        return replyMapper.toDto(reply);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        replyRepository.delete(reply);
        log.info("Reply deleted successfully: {}", reply);
    }

    @Override
    public List<ReplyResponseDto> getByPost(Long postId) {
        List<Reply> replies = replyRepository.findByPostId(postId);
        return replies.stream()
                .map(replyMapper::toDto)
                .collect(Collectors.toList());
    }
}
