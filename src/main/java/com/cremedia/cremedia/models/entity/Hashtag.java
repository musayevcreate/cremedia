package com.cremedia.cremedia.models.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "hashtags")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String text;


}
