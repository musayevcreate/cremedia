package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.Hashtag;

import java.util.Optional;


public interface HashtagService {
    Hashtag create(String text);
    Optional<Hashtag> getByText(String text);

    Hashtag findById(Long hashtagId);
}
