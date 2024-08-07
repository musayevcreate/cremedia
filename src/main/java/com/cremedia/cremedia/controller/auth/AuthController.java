package com.cremedia.cremedia.controller.auth;


import com.cremedia.cremedia.models.auth.AuthRequestDTO;
import com.cremedia.cremedia.models.auth.AuthenticationDTO;
import com.cremedia.cremedia.models.auth.UserRegisterDTO;
import com.cremedia.cremedia.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthenticationDTO login(@RequestBody @Valid AuthRequestDTO authRequestDto) {
        return authService.authenticate(authRequestDto);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        authService.register(userRegisterDTO);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username){
        authService.deleteUser(username);
    }

    @DeleteMapping("/enable/{username}")
    public void enable(@PathVariable String username){
        authService.enableUser(username);
    }

}
