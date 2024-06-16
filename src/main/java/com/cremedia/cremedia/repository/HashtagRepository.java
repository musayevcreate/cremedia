package com.cremedia.cremedia.repository;


import com.cremedia.cremedia.models.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Object findByText(String text);
}