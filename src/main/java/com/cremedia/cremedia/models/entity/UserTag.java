package com.cremedia.cremedia.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_tags")
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @Column(length = 50)
    private String tagName;


}
