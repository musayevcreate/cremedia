package com.cremedia.cremedia.models.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private double price;

    private String description;

}
