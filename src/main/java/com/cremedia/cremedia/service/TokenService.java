package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.entity.PasswordResetToken;
import com.cremedia.cremedia.models.entity.User;

import java.util.Optional;

public interface TokenService {

    PasswordResetToken createToken(User user);
    boolean isTokenValid(String token);
    void invalidateToken(String token);

    Optional<Object> findToken(String token);
}
