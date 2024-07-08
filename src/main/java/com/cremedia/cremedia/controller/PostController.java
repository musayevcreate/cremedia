package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.PostRequestDto;
import com.cremedia.cremedia.models.dto.response.PostResponseDto;
import com.cremedia.cremedia.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PostResponseDto> create(@Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto response = postService.create(postRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a Post by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable Long id) {
        PostResponseDto response = postService.getById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all Posts")
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAll() {
        List<PostResponseDto> response = postService.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all Posts by User ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDto>> getByUserId(@PathVariable Long userId) {
        List<PostResponseDto> response = postService.getByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a Post by ID")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long id, @Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto response = postService.update(id, postRequestDto);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a Post by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
