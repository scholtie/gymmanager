package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Subscription;
import com.schol.gymmanager.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s FROM Subscription s JOIN SubscriptionPlan sp on s.subscriptionPlan.id = sp.id JOIN Gym g on sp.gym.id = g.id WHERE g.id = ?1")
    List<Subscription> findAllByGymId(long gymId);
}
