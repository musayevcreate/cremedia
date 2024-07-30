package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.mapper.UserSubscriptionMapper;
import com.cremedia.cremedia.models.dto.request.*;
import com.cremedia.cremedia.models.dto.response.PasswordUpdateResponseDto;
import com.cremedia.cremedia.models.dto.response.UserResponseDto;
import com.cremedia.cremedia.models.dto.response.UserSubscriptionResponseDto;
import com.cremedia.cremedia.service.UserService;
import com.cremedia.cremedia.service.UserSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserSubscriptionService userSubscriptionService;
    private final UserSubscriptionMapper userSubscriptionMapper;

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

    @Operation(summary = "Update user")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto update( @Valid @RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
        return userService.update(userRequestDto,request);
    }
    @Operation(summary = "Update password")
    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public PasswordUpdateResponseDto updatePassword(@RequestBody PasswordUpdateRequestDto passwordUpdateRequestDto, HttpServletRequest request) {
        return userService.updatePassword(passwordUpdateRequestDto, request);
    }


    @Operation(summary = "Request password recovery")
    @PostMapping("/password-recovery")
    @ResponseStatus(HttpStatus.OK)
    public void requestPasswordRecovery(@Valid @RequestBody PasswordRecoveryRequestDto requestDto) {
        userService.requestPasswordRecovery(requestDto);
    }

    @Operation(summary = "Reset password token")
    @PostMapping("/password-reset")
    @ResponseStatus(HttpStatus.OK)
    public void resetPasswordToken(@Valid @RequestBody TokenBasedPasswordResetRequestDto requestDto, @RequestParam String token) {
        userService.resetPasswordToken(requestDto, token);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


    @Operation(summary = "Upgrade to premium")
    @PostMapping("/subscription-upgrade")
    public UserSubscriptionResponseDto upgradeToPremium(@Valid @RequestBody UserSubscriptionRequestDto requestDto, HttpServletRequest request) {
        return userSubscriptionService.upgradeToPremium(requestDto,request);
    }
}
