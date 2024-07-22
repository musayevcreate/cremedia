package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "Create a new Post")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto create(@Valid @RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.create(postRequestDto, request);
    }

    @Operation(summary = "Get a Post by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @Operation(summary = "Get all Posts")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> getAll() {
        return postService.getAll();
    }

    @Operation(summary = "Get all Posts by User ID")
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponseDto> getByUserId(@PathVariable Long userId) {
        return postService.getByUserId(userId);
    }

    @Operation(summary = "Update a Post by ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseDto update(@PathVariable Long id, @Valid @RequestBody PostRequestDto postRequestDto) {
        return postService.update(id, postRequestDto);
    }

    @Operation(summary = "Delete a Post by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
      postService.delete(id);
    }
}
