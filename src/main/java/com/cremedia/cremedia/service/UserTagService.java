package com.cremedia.cremedia.service;


import com.cremedia.cremedia.models.dto.request.UserTagRequestDto;
import com.cremedia.cremedia.models.dto.response.UserTagResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTagService {
    UserTagResponseDto create(UserTagRequestDto dto);

    UserTagResponseDto update(Long id, UserTagRequestDto dto);

    UserTagResponseDto getById(Long id);

    List<UserTagResponseDto> getAll();

    void delete(Long id);
}
