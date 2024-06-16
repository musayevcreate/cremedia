package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import jakarta.transaction.Transactional;

import java.util.List;

import java.util.List;

public interface PostService {

    PostResponseDto create(PostRequestDto postRequestDto);

    PostResponseDto getById(Long id);

    PostResponseDto update(Long id, PostRequestDto postRequestDto);

    List<PostResponseDto> getAll();

    void delete(Long id);

    List<PostResponseDto> getByUser(Long userId);


}
