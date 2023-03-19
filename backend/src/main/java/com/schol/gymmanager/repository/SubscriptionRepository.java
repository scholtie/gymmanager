package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
