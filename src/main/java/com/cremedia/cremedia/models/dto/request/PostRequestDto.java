package com.cremedia.cremedia.models.dto.request;


import com.cremedia.cremedia.models.entity.Emotion;
import com.cremedia.cremedia.models.entity.Hashtag;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostRequestDto {

    @NotNull
    private Long userId;

    @NotBlank
    private String content;
    private Long likes;
    private Long retweets;
    @ManyToMany
    @JoinTable(
            name = "post_hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<Hashtag> hashtags;
    private Set<Emotion> emotions;
    private Long comments;
    private String mentions;
    private String status;
    private String media;
    private Post replyTo;
    private LocalDateTime createdAt;
    private boolean isArchived;
    private String visibility;
}
