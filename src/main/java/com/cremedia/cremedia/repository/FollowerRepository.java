package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByFollowerId(Long followerId);
    List<Follower> findByFollowingId(Long followingId);
}
