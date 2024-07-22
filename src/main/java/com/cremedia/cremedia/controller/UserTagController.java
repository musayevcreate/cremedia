package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.UserTagRequestDto;
import com.cremedia.cremedia.models.dto.response.UserTagResponseDto;
import com.cremedia.cremedia.service.UserTagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/userTags")
@RequiredArgsConstructor
public class UserTagController {

    private final UserTagService userTagService;

    @Operation(summary = "Create a new UserTag")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserTagResponseDto create(@Valid @RequestBody UserTagRequestDto request) {
        return userTagService.create(request);
    }

    @Operation(summary = "Update an existing UserTag")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserTagResponseDto update(@PathVariable Long id, @Valid @RequestBody UserTagRequestDto request) {
        return userTagService.update(id, request);
    }

    @Operation(summary = "Get a UserTag by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserTagResponseDto getById(@PathVariable Long id) {
        return userTagService.getById(id);
    }

    @Operation(summary = "Get all UserTags")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserTagResponseDto> getAllUserTags() {
        return userTagService.getAll();
    }

    @Operation(summary = "Delete a UserTag by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userTagService.delete(id);
    }
}
