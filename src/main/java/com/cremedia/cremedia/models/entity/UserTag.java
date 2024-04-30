package com.cremedia.cremedia.models.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "user_tags")
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String tagName;


    @OneToOne
    @JoinColumn(name = "usersecondId", referencedColumnName = "id")
    private UsersSecond usersSecond;
}
