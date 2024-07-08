package com.cremedia.cremedia.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class PostRequestDto {

    @NotNull
    private Long userId;

    @NotBlank
    private String content;

    private Set<Long> hashtagIds;

    private String visibility;
}
