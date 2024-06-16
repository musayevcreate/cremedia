package com.cremedia.cremedia.service;

import com.cremedia.cremedia.models.dto.response.ArchivedPostResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;

import java.util.List;

public interface ArchivedPostService {
    ArchivedPostResponseDto archive(Post postId, User userId);
    ArchivedPostResponseDto unarchive(Post postId, User userId);
    List<ArchivedPostResponseDto> getAll(User userId);
}
