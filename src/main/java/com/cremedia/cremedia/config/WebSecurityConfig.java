package com.cremedia.cremedia.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig  {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/users/password-recovery").permitAll()
                                .requestMatchers("/api/users/password-reset").permitAll()
                                .requestMatchers(permitSwagger).permitAll()

                                .requestMatchers("/api/auth/login").permitAll()
                               .requestMatchers("/api/auth/register").permitAll()
                               .requestMatchers("/api/posts/**").hasAnyRole("USER")
                               .requestMatchers("/api/followers/**").hasAnyRole("USER")
                               .requestMatchers("/api/likes/**").hasAnyRole("USER")
                               .requestMatchers("/api/replies/**").hasAnyRole("USER")
                                 .requestMatchers("/api/users/**").hasAnyRole("USER")

                                .anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    public static String[] permitSwagger = {
            "v3/api-docs/**",
            "v3/api-docs.yaml",
            "swagger-ui/**",
            "/swagger-ui.html"
    };
}
