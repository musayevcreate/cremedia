package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.ArchivedPost;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivedPostRepository extends JpaRepository<ArchivedPost, Long>{
    ArchivedPost findByPostIdAndUserId(Post postId, User userId);

    List<ArchivedPost> findById(User userId);
}
