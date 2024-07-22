package com.cremedia.cremedia.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class PostRequestDto {

    @Schema(hidden = true)
    private Long userId;

    @NotBlank
    private String content;
}
