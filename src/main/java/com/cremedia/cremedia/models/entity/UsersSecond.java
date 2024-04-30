package com.cremedia.cremedia.models.entity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "usersSecond")
public class UsersSecond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private String status;
    private Long usersSecond;


}
