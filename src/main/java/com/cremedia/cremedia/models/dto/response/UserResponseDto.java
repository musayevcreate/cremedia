package com.cremedia.cremedia.models.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    @Schema(hidden = true)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String number;
    private String avatar;
    private String bio;
    private String tag;
    private Long followersCount;
    private Long followingsCount;
    private LocalDate dateOfBirth;
    private String gender;
    private String country;
    private Boolean visibility;
    private Boolean isActive;
    private Boolean isPro;
    private Boolean isVerified;
    private LocalDateTime lastLogin;
}
