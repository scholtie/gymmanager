package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByBaseUser(BaseUser baseUser);
}
