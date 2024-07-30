package com.cremedia.cremedia.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSubscriptionRequestDto {

    @NotNull
    private String cardNumber;

    @NotNull
    private String expiryDate;

    @NotNull
    private String cvv;
}
