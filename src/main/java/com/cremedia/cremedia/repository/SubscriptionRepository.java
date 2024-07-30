package com.cremedia.cremedia.repository;

import com.cremedia.cremedia.models.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
    List<Subscription> findAllByEndDateBeforeAndIsActive(LocalDate endDate, boolean isActive);

}
