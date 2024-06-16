package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.mapper.UserMapper;
import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
        log.info("User created: {}", user);
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("User retrieved: {}", user);
        return userMapper.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        log.info("All users retrieved: {}", users);
        return users.stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Action is failed");
                   return new RuntimeException("User not found");
                });
        userMapper.updateFromDto(userRequestDto, user);
        userRepository.save(user);
        log.info("User updated: {}", user);
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("User deleted with id: {}", id);
    }
}
