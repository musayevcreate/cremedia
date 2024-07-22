package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.exception.EntityNotFoundException;
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
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        log.info("update method is started.");
        var user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Action is failed");
                   return new EntityNotFoundException("USER_NOT_FOUND");
                });
        userMapper.updateFromDto(userRequestDto, user);
        userRepository.save(user);
        log.info("User updated: {}", user);
        log.info("update method is finished.");
        return userMapper.toResponseDto(user);
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
