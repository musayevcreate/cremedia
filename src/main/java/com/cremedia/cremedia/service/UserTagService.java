package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.UserTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface UserTagService {

    UserTag getById(Long id) ;

    Page<UserTag> getAll(PageRequest pageable);

    UserTag add(UserTag userTag);
    UserTag update(UserTag userTag);
    void delete(Long id);
    Page<UserTag> getAll(Pageable pageable);
}
