package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.enums.SubscriptionPlanType;
import com.cremedia.cremedia.exception.EntityNotFoundException;
import com.cremedia.cremedia.exception.PaymentException;
import com.cremedia.cremedia.mapper.UserSubscriptionMapper;
import com.cremedia.cremedia.models.dto.request.UserSubscriptionRequestDto;
import com.cremedia.cremedia.models.dto.response.UserSubscriptionResponseDto;
import com.cremedia.cremedia.models.entity.Subscription;
import com.cremedia.cremedia.models.entity.UserTag;
import com.cremedia.cremedia.repository.SubscriptionRepository;
import com.cremedia.cremedia.repository.UserRepository;
import com.cremedia.cremedia.repository.UserTagRepository;
import com.cremedia.cremedia.service.PaymentService;
import com.cremedia.cremedia.service.UserSubscriptionService;
import com.cremedia.cremedia.utility.ExtractorHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final UserSubscriptionMapper userSubscriptionMapper;
    private final ExtractorHelper extractorHelper;
    private final SubscriptionRepository subscriptionRepository;


    @Scheduled(cron = "0 0 0 * * ?")
    public void checkExpiredSubscriptions() {
        LocalDate today = LocalDate.now();
        List<Subscription> expiredSubscriptions = subscriptionRepository.findAllByEndDateBeforeAndIsActive(today, true);

        for (Subscription subscription : expiredSubscriptions) {
            subscription.setSubscriptionPlan(SubscriptionPlanType.FREE);
            subscription.setIsActive(false);
            subscriptionRepository.save(subscription);
        }
    }


    @Override
        public UserSubscriptionResponseDto upgradeToPremium(UserSubscriptionRequestDto requestDto, HttpServletRequest request) {
            String extractedUsername = extractorHelper.extractUsername(request);
            var user = userRepository.findUserByUsername(extractedUsername)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + extractedUsername));

            boolean paymentSuccess = paymentService.processPayment(user, new BigDecimal("20.00"), requestDto.getCardNumber(), requestDto.getExpiryDate(), requestDto.getCvv());

            if (paymentSuccess) {
                user.setSubscriptionPlan(SubscriptionPlanType.PREMIUM);
                user.setHasBlueTick(true);
                user.setTagName("Premium User");

                if (user.getTags() == null) {
                    user.setTags(new HashSet<>());
                }

                userRepository.save(user);
                log.info("User {} upgraded to premium plan", user.getUsername());
                return userSubscriptionMapper.toDto(user);
            } else {
                log.error("Payment failed for user {}", user.getUsername());
                throw new PaymentException("Payment failed for user: " + user.getUsername());
            }
        }


    }



//FIXME bunu duzelt , is pro is verified mentiqine bax post ucun media ve profil sekli ucun media uploada bax
