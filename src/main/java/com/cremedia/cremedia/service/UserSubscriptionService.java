package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.request.UserSubscriptionRequestDto;
import com.cremedia.cremedia.models.dto.response.UserSubscriptionResponseDto;
import com.cremedia.cremedia.models.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserSubscriptionService {

    UserSubscriptionResponseDto upgradeToPremium(UserSubscriptionRequestDto requestDto, HttpServletRequest request);
}