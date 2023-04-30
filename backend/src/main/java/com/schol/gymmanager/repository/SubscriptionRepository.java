package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Subscription;
import com.schol.gymmanager.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s FROM " +
            "Subscription s JOIN SubscriptionPlan sp " +
            "on s.subscriptionPlan.id = sp.id " +
            "JOIN Gym g on sp.gym.id = g.id " +
            "WHERE g.id = ?1 AND " +
            "((s.cancelAtPeriodEnd = true AND s.currentPeriodEnd > CURRENT_DATE) " +
            "OR s.cancelAtPeriodEnd = false)")
    List<Subscription> findAllByGymId(long gymId);
    @Query("SELECT s FROM Subscription s WHERE s.customer.id = ?1 AND ((s.cancelAtPeriodEnd = true AND s.currentPeriodEnd > CURRENT_DATE) OR s.cancelAtPeriodEnd = false)")
    List<Subscription> findAllByCustomerId(long customerId);
}
