package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    boolean existsUserAccountByEmail (String email);
}
