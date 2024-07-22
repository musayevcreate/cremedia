package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.HashtagRequestDto;
import com.cremedia.cremedia.models.dto.response.HashtagResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.service.HashtagService;
import com.cremedia.cremedia.mapper.HashtagMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public HashtagResponseDto create(@RequestBody HashtagRequestDto request) {
        Hashtag hashtag = hashtagService.create(request.getText());
        return HashtagMapper.toDto(hashtag);
    }

    @Operation(summary = "Get Hashtag by text")
    @GetMapping("/{text}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Hashtag> getByText(@PathVariable String text) {
     return hashtagService.getByText(text);

    }
}
