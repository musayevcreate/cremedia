package com.cremedia.cremedia.service.Impl;
import com.cremedia.cremedia.exception.EntityNotFoundException;
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
@RequiredArgsConstructor
public class UserTagServiceImpl implements UserTagService {


    private final UserTagRepository userTagRepository;
    private final UserRepository userRepository;
    private final UserTagMapper userTagMapper;


    @Override
    @Transactional
    public UserTagResponseDto create(UserTagRequestDto dto) {
        log.info("Usertagcreate method is started.");
        var userTag = userTagMapper.toEntity(dto);

        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
        user.setTagName(dto.getTagName());
        userRepository.save(user);

        userTag.setUser(user);
        userTag = userTagRepository.save(userTag);
        log.info("Usertagcreate method is finished.");
        return userTagMapper.toDto(userTag);
    }

    @Override
    @Transactional
    public UserTagResponseDto update(Long id, UserTagRequestDto dto) {
        log.info("Usertagupdate method is started.");
        var userTag = userTagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("USERTAG_NOT_FOUND"));

        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
        user.setTagName(dto.getTagName());
        userRepository.save(user);

        userTag.setTagName(dto.getTagName());
        userTag = userTagRepository.save(userTag);
        log.info("Usertagupdate method is finished.");
        return userTagMapper.toDto(userTag);
    }

    @Transactional(readOnly = true)
    public UserTagResponseDto getById(Long id) {
        log.info("UsertaggetById method is started.");
        UserTag userTag = userTagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("USERTAG_NOT_FOUND"));
        log.info("UsertaggetById method is finished.");
        return userTagMapper.toDto(userTag);

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTagResponseDto> getAll() {
        log.info("UsertaggetAll method is started.");
        List<UserTag> userTags = userTagRepository.findAll();
        log.info("UsertaggetAll method is finished.");
        return userTags.stream()
                .map(userTagMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Usertagdelete method is started.");
        userTagRepository.deleteById(id);
        log.info("Usertag deleted with id: {}", id);
    }
}
