package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByGymId(long gymId);
    List<Review> findAllByCustomerId(long customerId);
    List<Review> findAllByTrainerId(long trainerId);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.gym.id = ?1")
    Long getAverageRatingGym(long gymId);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.trainer.id = ?1")
    Long getAverageRatingTrainer(long trainerId);
}
