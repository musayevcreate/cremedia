package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.Hashtag;

import java.util.Optional;
import java.util.Set;


public interface HashtagService {
    Hashtag create(String text);
    Optional<Hashtag> getByText(String text);
    Set<Hashtag> extractHashtags(String content);
    Hashtag findById(Long hashtagId);
}
