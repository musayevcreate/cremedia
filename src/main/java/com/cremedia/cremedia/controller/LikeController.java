package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.mapper.LikeMapper;
import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "Like a post")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void like(@Valid @RequestBody LikeRequestDto likeRequestDto, HttpServletRequest request) {
        likeService.likePost(likeRequestDto, request);
    }

    @Operation(summary = "Unlike a post")
    @DeleteMapping("/{likeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlike(@PathVariable Long likeId) {
        likeService.unlikePost(likeId);
    }

    @Operation(summary = "get all post likes by users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts/{postId}/likes")
    public List<LikeResponseDto> getLikesForPost(@PathVariable Long postId) {
        return likeService.getLikesForPost(postId);
    }

}
