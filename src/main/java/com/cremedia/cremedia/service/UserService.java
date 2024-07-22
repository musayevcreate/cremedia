package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserResponseDto getById(Long id);

    List<UserResponseDto> getAll();

    UserResponseDto update(Long id, UserRequestDto userRequestDto);

    void delete(Long id);

    User findById(Long userId);
    
}
