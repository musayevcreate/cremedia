package com.cremedia.cremedia.models.dto.request;

import lombok.Data;

@Data
public class PasswordUpdateRequestDto {
    private String oldPassword;
    private String newPassword;
}