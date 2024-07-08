package com.cremedia.cremedia.models.dto.request;

import com.cremedia.cremedia.models.entity.User;
import lombok.Data;

@Data
public class UserTagRequestDto {
    private Long userId;
    private String tagName;
}