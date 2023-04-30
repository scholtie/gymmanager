package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BaseUser;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findByBaseUser(BaseUser baseUser);
}
