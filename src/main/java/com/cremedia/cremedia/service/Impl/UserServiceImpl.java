package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.exception.EntityNotFoundException;
import com.cremedia.cremedia.mapper.UserMapper;
import com.cremedia.cremedia.models.dto.request.PasswordRecoveryRequestDto;
import com.cremedia.cremedia.models.dto.request.PasswordUpdateRequestDto;
import com.cremedia.cremedia.models.dto.request.TokenBasedPasswordResetRequestDto;
import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.PasswordUpdateResponseDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.PasswordResetToken;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.PasswordResetTokenRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.UserService;

import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bcryptPasswordEncoder;
    private final ExtractorHelper extractorHelper;
    private final PasswordResetTokenRepository tokenRepository;
    private final TokenServiceImpl tokenService;
    private final EmailServiceImpl emailService;




    @Override
    public UserResponseDto getById(Long id) {
        log.info("getById method is started.");
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
        log.info("User retrieved: {}", user);
        log.info("getById method is finished.");
        return userMapper.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        log.info("getAll method is started.");
        List<User> users = userRepository.findAll();
        log.info("All users retrieved: {}", users);
        log.info("getAll method is finished.");
        return users.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDto update( UserRequestDto userRequestDto, HttpServletRequest request) {
        String username = extractorHelper.extractUsername(request);

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateFromDto(userRequestDto, user);
        userRepository.save(user);
        log.info("User updated: {}", user);
        log.info("update method is finished.");
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public PasswordUpdateResponseDto updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto, HttpServletRequest request) {
        String username = extractorHelper.extractUsername(request);

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!bcryptPasswordEncoder.matches(passwordUpdateRequestDto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(bcryptPasswordEncoder.encode(passwordUpdateRequestDto.getNewPassword()));
        userRepository.save(user);

        return new PasswordUpdateResponseDto("Password updated successfully");
    }
    @Override
    @Transactional
    public void requestPasswordRecovery(PasswordRecoveryRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpirationDate(LocalDateTime.now().plusMinutes(5));
        tokenRepository.save(token);

        emailService.sendMail(user.getEmail(), token.getToken());
        log.info("Password recovery email sent to: {}", user.getEmail());
    }

    @Override
    @Transactional
    public void resetPasswordToken(TokenBasedPasswordResetRequestDto requestDto, String token) {
        PasswordResetToken passwordResetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found or expired"));

        if (LocalDateTime.now().isAfter(passwordResetToken.getExpirationDate())) {
            throw new RuntimeException("Token has expired");
        }

        User user = passwordResetToken.getUser();
        user.setPassword(bcryptPasswordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);

        log.info("Password reset successfully for user: {}", user.getEmail());

        tokenRepository.delete(passwordResetToken); // Invalidate the token after use

    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("delete method is started.");
        userRepository.deleteById(id);
        log.info("User deleted with id: {}", id);
    }

    @Override
    public User findById(Long userId) {
        return null;
    }
}
