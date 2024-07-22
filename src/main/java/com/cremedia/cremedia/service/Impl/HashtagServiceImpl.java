package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.repository.HashtagRepository;
import com.cremedia.cremedia.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Set<Hashtag> extractHashtags(String content) {
        Set<Hashtag> hashtags = new HashSet<>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String text = matcher.group().substring(1).toLowerCase(); // Remove '#' and convert to lowercase
            Hashtag hashtag = hashtagRepository.findByText(text)
                    .orElseGet(() -> {
                        Hashtag newHashtag = new Hashtag();
                        newHashtag.setText(text);
                        return hashtagRepository.save(newHashtag);
                    });
            hashtags.add(hashtag);
        }
        return hashtags;
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
