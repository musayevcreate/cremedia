package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.PasswordRecoveryRequestDto;
import com.cremedia.cremedia.models.dto.request.PasswordUpdateRequestDto;
import com.cremedia.cremedia.models.dto.request.TokenBasedPasswordResetRequestDto;
import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.PasswordUpdateResponseDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    UserResponseDto getById(Long id);

    List<UserResponseDto> getAll();

    UserResponseDto update(UserRequestDto userRequestDto, HttpServletRequest request);

    PasswordUpdateResponseDto updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto, HttpServletRequest request);

    void requestPasswordRecovery(PasswordRecoveryRequestDto requestDto);

    void resetPasswordToken(@Valid TokenBasedPasswordResetRequestDto requestDto, String token);

    void delete(Long id);

    User findById(Long userId);
    
}
