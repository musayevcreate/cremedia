package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.models.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{

}
