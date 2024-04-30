package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.entity.UserTag;
import com.cremedia.cremedia.repository.UserTagRepository;
import com.cremedia.cremedia.service.UserTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserTagImpl implements UserTagService {

    private final UserTagRepository userTagRepository;

    @Override
    public UserTag getById(Long userId){
       return userTagRepository.findById(userId).get();
    }

    @Override
    public Page<UserTag> getAll(PageRequest pageable) {
        return null;
    }

    @Override
    public UserTag add(UserTag userTag) {
        log.info("UserTag added: {}", userTag);
       return userTagRepository.save(userTag);
    }

    @Override
    public UserTag update(UserTag userTag) {
        log.info("UserTag updated: {}", userTag);
        return userTagRepository.save(userTag);
    }

    @Override
    public void delete(Long id) {
        log.info("UserTag deleted: {}", id);
        userTagRepository.deleteById(id);
    }

    @Override
    public Page<UserTag> getAll(Pageable pageable) {
        return userTagRepository.findAll(pageable);

    }
}

