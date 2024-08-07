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
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
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
    private final PostRepository postRepository;
    private final ReplyMapper replyMapper;
    private final ExtractorHelper extractorHelper;
    private final UserRepository userRepository;

    @Override
    public ReplyResponseDto create(ReplyRequestDto requestDto, HttpServletRequest request) {
        log.info("reply create method is started.");
        String extractedUsername = extractorHelper.extractUsername(request);
        var user = userRepository.findUserByUsername(extractedUsername)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
        requestDto.setUserId(user.getId());
        var reply = replyMapper.toEntity(requestDto);

        if (requestDto.getPostId() != null) {
            var post = postRepository.findById(requestDto.getPostId())
                    .orElseThrow(() -> new EntityNotFoundException("POST_NOT_FOUND"));
            reply.setPost(post);

            var savedReply = replyRepository.save(reply);

            post.getReplies().add(savedReply);
            postRepository.save(post);

            log.info("Reply created successfully for post: {}", post.getId());

            post.setRepliesCount(post.getRepliesCount() + 1);
            return replyMapper.toDto(savedReply);
        } else {
            throw new EntityNotFoundException("Post ID cannot be null for creating a reply.");
        }
    }



    @Override
    public ReplyResponseDto getById(Long id) {
        log.info("getById method is started.");
        var reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        log.info("Reply retrieved: {}", reply);
        return replyMapper.toDto(reply);
    }

    @Override
    @Transactional
    public ReplyResponseDto update(Long id, ReplyRequestDto replyRequestDto) {
        log.info("update method is started.");
        var reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        replyMapper.updateFromDto(replyRequestDto, reply);
        replyRepository.save(reply);
        log.info("Reply updated successfully: {}", reply);
        return replyMapper.toDto(reply);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("delete method is started.");
        var reply = replyRepository.findById(id)
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
