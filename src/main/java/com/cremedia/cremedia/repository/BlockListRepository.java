package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.BlockList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockListRepository extends JpaRepository<BlockList, Long> {
}
