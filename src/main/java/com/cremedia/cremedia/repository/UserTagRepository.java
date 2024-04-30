package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Long>{
}
