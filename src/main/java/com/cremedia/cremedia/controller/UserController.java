package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.UserRequestDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getById(@PathVariable Long id) {
        log.info("Fetching user with ID: {}", id);

        UserResponseDto response = userService.getById(id);

        if (response != null) {
            log.info("User found: {}", response);
            return response;
        } else {
            log.info("User with ID {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Operation(summary = "Get all users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAll() {
        log.info("Fetching all users");
        List<UserResponseDto> response = userService.getAll();
        log.info("Fetched {} users", response.size());

        return response;
    }

    @Operation(summary = "Update user by ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto update(@PathVariable Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Updating user with ID: {}", id);

        UserResponseDto response = userService.update(id, userRequestDto);

        if (response != null) {
            log.info("User updated successfully: {}", response);
            return response;
        } else {
            log.info("User with ID {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.delete(id);
        log.info("User deleted successfully");
    }
}
