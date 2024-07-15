package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.repository.HashtagRepository;
import com.cremedia.cremedia.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;

    @Override
    public Hashtag create(String text) {
        Hashtag hashtag = new Hashtag();
        hashtag.setText(text);
        return hashtagRepository.save(hashtag);
    }

    @Override
    public Optional<Hashtag> getByText(String text) {
        return hashtagRepository.findByText(text);
    }

    @Override
    public Hashtag findById(Long hashtagId) {
        return null;
    }


}
