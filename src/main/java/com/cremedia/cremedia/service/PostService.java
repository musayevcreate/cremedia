package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {

    PostResponseDto create(PostRequestDto requestDto);

    PostResponseDto getById(Long id);

    List<PostResponseDto> getAll();

    List<PostResponseDto> getByUserId(Long userId);

    PostResponseDto update(Long id, PostRequestDto requestDto);

    void delete(Long id);
}
