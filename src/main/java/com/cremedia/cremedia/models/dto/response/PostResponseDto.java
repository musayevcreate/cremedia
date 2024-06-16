package com.cremedia.cremedia.models.dto.response;

import com.cremedia.cremedia.models.entity.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PostResponseDto {

    private Long id;
    private Long userId;
    private String content;
    private Long likes;
    private Long retweets;
    @ManyToMany
    @JoinTable(
            name = "post_hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<Hashtag> hashtags = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Emotion> emotions = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<Reply> replies = new HashSet<>();

    private Long comments;
    private String mentions;

    private String status;
    private String media;
    private Post replyTo;
    private boolean isArchived;
    private String visibility;
}
