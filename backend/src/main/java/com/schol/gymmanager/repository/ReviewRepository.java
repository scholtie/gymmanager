package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Review;
import com.schol.gymmanager.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByGymId(long gymId);
    List<Review> findAllByCustomerId(long customerId);
    List<Review> findAllByTrainerId(long trainerId);
}
