package com.cremedia.cremedia.models.dto.response;

import lombok.Data;

@Data
public class UserTagResponseDto {
    private Long id;

    private Long userId;
    private String tagName;
}