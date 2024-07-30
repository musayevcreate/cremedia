package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.Follower;
import com.cremedia.cremedia.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByFollowerId(Long followerId);
    List<Follower> findByFollowingId(Long followingId);

    boolean existsByFollowerAndFollowing(User followerUser, User followingUser);
    Optional<Follower> findByFollowerAndFollowing(User followerUser, User followingUser);
}
