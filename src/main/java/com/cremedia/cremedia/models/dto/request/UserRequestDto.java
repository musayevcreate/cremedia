package com.cremedia.cremedia.models.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @NotBlank
    private String number;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, and 1 digit, with a minimum length of 8 characters")

    private String password;

    private String avatar;
    private String bio;
    private String tag;

    @NotNull
    private LocalDate dateOfBirth;

    private String gender;
    private String country;
    private Boolean visibility;
    private Boolean isActive;
    private Boolean isPro;
    private Boolean isVerified;
    private LocalDate lastLogin;
}
