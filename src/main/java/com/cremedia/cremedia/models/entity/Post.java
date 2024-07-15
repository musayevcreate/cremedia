package com.cremedia.cremedia.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "content")
    @Lob // what is this?
    private String content;


    private Long likes = 0L;


    private Long retweets = 0L;

    private String mentions;

    @Lob
    private String media;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<Hashtag> hashtags;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Emotion> emotions = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "reply_to")
    private Post replyTo;

//    @ManyToOne
//    @JoinColumn(name = "status", nullable = false)
//    private Status status;
//
//    public Post() {
//        this.status = new Status();
//    }

    private LocalDateTime createdAt;

    private boolean isArchived;

}