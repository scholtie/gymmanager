package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findByBaseUser(BaseUser baseUser);
}
