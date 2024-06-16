package com.cremedia.cremedia.service.auth;

import com.cremedia.cremedia.mapper.UserMapper;
import com.cremedia.cremedia.models.auth.AuthRequestDTO;
import com.cremedia.cremedia.models.auth.AuthenticationDTO;
import com.cremedia.cremedia.models.auth.RoleDTO;
import com.cremedia.cremedia.models.auth.UserRegisterDTO;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserMapper userMapper;

    public void register(UserRegisterDTO userRegisterDTO) {
        var user = UserRegisterDTO.builder()
                .username(userRegisterDTO.getUsername())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .email(userRegisterDTO.getEmail())
                .name(userRegisterDTO.getName())
                .surname(userRegisterDTO.getSurname())
                .number(userRegisterDTO.getNumber())
                .roles(userRegisterDTO.getRoles())
                .isEnabled(true)
                .build();

        userRepository.save(userMapper.toRegisterEntity(user));
    }

//    public void login(String username, String password, Set<RoleDTO> roles) {
//        var user = UserRegisterDTO.builder()
//                .username(username)
//                .password(passwordEncoder.encode(password))
//                .roles(roles)
//                .isEnabled(true)
//                .build();
//
//        userRepository.save(userMapper.toRegisterEntity(user));
//    }

    public AuthenticationDTO authenticate(AuthRequestDTO authRequestDTO) {
        log.info("ActionLog.authenticate.start by: {}", authRequestDTO.getUsername());

        User user = userRepository.findUserByUsername(authRequestDTO.getUsername())
                .orElseThrow(
                        () -> new BadCredentialsException("INVALID_USERNAME_OR_PASSWORD")
                );

        try {

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );

            var jwtToken = jwtService.generateToken(user);
            log.info("ActionLog.authenticate.end logged in: {}",  user.getUsername());
            return AuthenticationDTO.builder().token(jwtToken).build();
        } catch (BadCredentialsException e){
            log.error("Error due to {} ", e.getMessage());
            throw new BadCredentialsException("INVALID_USERNAME_OR_PASSWORD");
        }
    }

    public void deleteUser(String username) {
        log.info("ActionLog.deleteUser.start");
        var user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("USER_NOT_FOUND")
        );
        user.setIsEnabled(false);
        userRepository.save(user);
        log.info("ActionLog.deleteUser.end");
    }

    public void enableUser(String username) {
        log.info("ActionLog.enableUser.start");
        var user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("USER_NOT_FOUND")
        );
        user.setIsEnabled(true);
        userRepository.save(user);
        log.info("ActionLog.enableUser.end");
    }

}

