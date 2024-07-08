package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.mapper.UserTagMapper;
import com.cremedia.cremedia.models.dto.request.UserTagRequestDto;
import com.cremedia.cremedia.models.dto.response.UserTagResponseDto;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.models.entity.UserTag;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.repository.UserTagRepository;
import com.cremedia.cremedia.service.UserTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserTagServiceImpl implements UserTagService {


    private final UserTagRepository userTagRepository;
    private final UserRepository userRepository;
    private final UserTagMapper userTagMapper;

    public UserTagServiceImpl(UserTagRepository userTagRepository, UserRepository userRepository, UserTagMapper userTagMapper) {
        this.userTagRepository = userTagRepository;
        this.userRepository = userRepository;
        this.userTagMapper = userTagMapper;
    }

    @Override
    @Transactional
    public UserTagResponseDto create(UserTagRequestDto dto) {
        UserTag userTag = userTagMapper.toEntity(dto);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setTagName(dto.getTagName());
        userRepository.save(user);

        userTag.setUser(user);
        userTag = userTagRepository.save(userTag);
        return userTagMapper.toDto(userTag);
    }

    @Override
    @Transactional
    public UserTagResponseDto update(Long id, UserTagRequestDto dto) {
        UserTag userTag = userTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTag not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setTagName(dto.getTagName());
        userRepository.save(user);

        userTag.setTagName(dto.getTagName());
        userTag = userTagRepository.save(userTag);
        return userTagMapper.toDto(userTag);
    }

    @Transactional(readOnly = true)
    public UserTagResponseDto getById(Long id) {
        UserTag userTag = userTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTag not found"));
        return userTagMapper.toDto(userTag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTagResponseDto> getAll() {
        List<UserTag> userTags = userTagRepository.findAll();
        return userTags.stream()
                .map(userTagMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userTagRepository.deleteById(id);
    }
}
