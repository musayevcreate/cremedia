package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.service.HashtagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtags")
@Slf4j
public class HashtagController {

    private final HashtagService hashtagService;

    @Operation(summary = "Create a new Hashtag")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Hashtag create(@RequestParam String text) {
        Hashtag hashtag = hashtagService.create(text);
        log.info("Hashtag created: {}", hashtag);
        return hashtag;
    }

    @Operation(summary = "Get Hashtag by text")
    @GetMapping("/{text}")
    @ResponseStatus(HttpStatus.OK)
    public Hashtag getByText(@PathVariable String text) {
        Optional<Hashtag> hashtag = hashtagService.getByText(text);
        if (hashtag.isEmpty()) {
            log.info("Hashtag with text '{}' not found", text);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hashtag not found");
        }
        log.info("Hashtag found: {}", hashtag);
        return hashtag.orElse(null);
    }
}
