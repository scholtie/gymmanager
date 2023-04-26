package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByCustomerId(long customerId);
}
