package com.cremedia.cremedia.models.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "emotions")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "type", length = 50)
    private String type;

}
