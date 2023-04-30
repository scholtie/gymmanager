package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BaseUser;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByBaseUser(BaseUser baseUser);
}
