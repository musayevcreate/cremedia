package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.Reply;
import com.cremedia.cremedia.models.entity.User;

import java.util.List;

public interface ReplyService {
    ReplyResponseDto getById(Long id);
    ReplyResponseDto update(Long id, ReplyRequestDto replyRequestDto);
    void delete(Long id);
    List<ReplyResponseDto> getByPost(Long postId);

    ReplyResponseDto create(ReplyRequestDto replyRequestDto);
}
