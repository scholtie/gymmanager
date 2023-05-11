package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.user.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByCustomerOrderByStartAsc(Customer customer);
    List<Session> findAllByTrainerOrderByStartAsc(Trainer trainer);
}
