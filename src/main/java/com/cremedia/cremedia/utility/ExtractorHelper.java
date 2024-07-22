package com.cremedia.cremedia.utility;

import com.cremedia.cremedia.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ExtractorHelper {
    private final JwtService jwtService;

    public String extractUsername(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        return jwtService.extractUsername(token);
    }
}