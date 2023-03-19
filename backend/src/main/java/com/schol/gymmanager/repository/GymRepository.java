package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
