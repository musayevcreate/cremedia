package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.mapper.ReplyMapper;
import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Reply;
import com.cremedia.cremedia.repository.ReplyRepository;
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
    private final ReplyMapper replyMapper;

    @Override
    @Transactional
    public ReplyResponseDto create(ReplyRequestDto replyRequestDto) {
        log.info("Creating reply: {}", replyRequestDto);
        Long postId = replyRequestDto.getPostId();
        if (postId == null) {
            log.error("Post ID is null in reply request: {}", replyRequestDto);
            throw new IllegalArgumentException("Post ID cannot be null");
        }

        Reply reply = replyMapper.toEntity(replyRequestDto);
        replyRepository.save(reply);
        return replyMapper.toResponseDto(reply);
    }


    @Override
    public ReplyResponseDto getById(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        return replyMapper.toResponseDto(reply);
    }

    @Override
    @Transactional
    public ReplyResponseDto update(Long id, ReplyRequestDto replyRequestDto) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reply not found with id: " + id));
        replyMapper.updateFromDto(replyRequestDto, reply);
        replyRepository.save(reply);
        log.info("Reply updated successfully: {}", reply);
        return replyMapper.toResponseDto(reply);
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
                .map(replyMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
