package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.mapper.LikeMapper;
import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
@Slf4j
public class LikeController {

    private final LikeService likeService;
    private final LikeMapper likeMapper;

    @Operation(summary = "Like a post")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void like(@Valid @RequestBody LikeRequestDto likeRequestDto) {
        likeService.likePost(likeRequestDto);
        log.info("Post liked: {}", likeRequestDto);
    }

    @Operation(summary = "Unlike a post")
    @DeleteMapping("/{likeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlike(@PathVariable Long likeId) {
        likeService.unlikePost(likeId);
        log.info("Post unliked with ID: {}", likeId);
    }
}
