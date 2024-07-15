package com.cremedia.cremedia.models.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String password;

    private Boolean isEnabled;

    private String avatar;

    private String bio;
    private LocalDate dateOfBirth;
    private String gender;
    private String country;

//    @ManyToOne
//    @JoinColumn(name = "status", nullable = false)
//    private Status status;
//
//    public User() {
//        this.status = new Status();
//    }

    @Column(nullable = false)
    private boolean isPro;

    @Column(nullable = false)
    private boolean isVerified;

    @OneToMany(mappedBy = "user")
    private Set<UserTag> tags;

    @Column(length = 50)
    private String tagName;

    @Column(nullable = false)
    private LocalDateTime lastLogin;


    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private Set<Follower> followers = new HashSet<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private Set<Follower> following = new HashSet<>();

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @PrePersist
    protected void onCreate(){
        this.avatar = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
        this.bio = "Hello, I'm new here!";
        this.isEnabled = true;
        this.isPro = false;
        this.isVerified = false;
        this.lastLogin = LocalDateTime.now();
    }


//    private Long followers;
//    private Long followings;


}

