package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.Hashtag;


public interface HashtagService {
    Hashtag create(String text);
    Hashtag getByText(String text);
}
