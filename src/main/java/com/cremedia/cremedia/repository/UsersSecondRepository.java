package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.UsersSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersSecondRepository extends JpaRepository<UsersSecond, Long>{

    @Query("SELECT u FROM UsersSecond u WHERE u.username = ?1")
    UsersSecond findByUsername(String username);

}
