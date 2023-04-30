package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByCustomer(Customer customer);
    List<Session> findAllByTrainer(Trainer trainer);
}
