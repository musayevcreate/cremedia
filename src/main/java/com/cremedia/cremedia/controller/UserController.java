package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Get all users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Update user by ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto update(@PathVariable Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
