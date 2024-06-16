package com.cremedia.cremedia.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(permitSwagger).permitAll()
                                .requestMatchers("/api/**").permitAll()
//                                .requestMatchers("/api/v1/auth/login").permitAll()
//                                .requestMatchers("/api/v1/applications/public/**").permitAll()
//                                .requestMatchers("/api/v1/applications/admin/**").hasAnyRole("ADMIN")
//                                .requestMatchers("/api/v1/blogs/public/**").permitAll()
//                                .requestMatchers("/api/v1/blogs/teacher/**").hasAnyRole(TEACHER)
//                                .requestMatchers("/api/v1/courses/public").permitAll()
//                                .requestMatchers("/api/v1/courses/admin").hasAnyRole("ADMIN")
//                                .requestMatchers("/api/v1/students/admin").hasAnyRole("ADMIN")
//                                .requestMatchers("/api/v1/students/student").hasAnyRole(STUDENT)
//                                .requestMatchers("/api/v1/teachers/public").permitAll()
//                                .requestMatchers("/api/v1/teachers/teacher").hasAnyRole(TEACHER)
//                                .requestMatchers("/api/v1/teachers/admin").hasAnyRole("ADMIN")

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
