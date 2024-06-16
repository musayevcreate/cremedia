package com.cremedia.cremedia.models.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {
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
    private String password;

    private Set<RoleDTO> roles;

//    private String avatar;
//    private String bio;
//    private String tag;
//
////    @NotNull
////    private LocalDate dateOfBirth;
//
//    private String gender;
//    private String country;
//    private Boolean visibility;
    private Boolean isEnabled;
//    private Boolean isActive;
//    private Boolean isPro;
//    private Boolean isVerified;
//    private LocalDate lastLogin;
}
