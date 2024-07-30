package com.cremedia.cremedia.models.entity;


import com.cremedia.cremedia.enums.SubscriptionPlanType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private SubscriptionPlanType planType;
    private BigDecimal price;
    private String description;

}
