package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.Follower;
import com.cremedia.cremedia.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Follower findByFollowerAndFollowing(User follower, User following);
}
