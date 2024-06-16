package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;

    @PostMapping("/create")
    public ResponseEntity<Hashtag> create(@RequestParam String text) {
        Hashtag hashtag = hashtagService.create(text);
        return new ResponseEntity<>(hashtag, HttpStatus.CREATED);
    }

    @GetMapping("/{text}")
    public ResponseEntity<Hashtag> getByName(@PathVariable String text) {
        Hashtag hashtag = hashtagService.getByText(text);
        if (hashtag != null) {
            return new ResponseEntity<>(hashtag, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
