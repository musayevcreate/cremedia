package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.models.entity.PasswordResetToken;
import com.cremedia.cremedia.models.entity.User;
import com.cremedia.cremedia.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken createToken(User user) {
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpirationDate(LocalDateTime.now().plusMinutes(5));
        return tokenRepository.save(token);
    }

    public boolean isTokenValid(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElse(null);
        if (resetToken == null) {
            return false;
        }
        return LocalDateTime.now().isBefore(resetToken.getExpirationDate());
    }

    public void invalidateToken(String token) {
        tokenRepository.findByToken(token).ifPresent(tokenRepository::delete);
    }

    @Override
    public Token allocateToken(String extendedInformation) {
        return null;
    }

    @Override
    public Token verifyToken(String key) {
        return null;
    }
}

