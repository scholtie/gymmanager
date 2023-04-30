package com.schol.gymmanager.repository;


import com.schol.gymmanager.model.progress.NumericGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumericGoalRepository extends JpaRepository<NumericGoal, Long> {
    List<NumericGoal> findAllByCustomerId(Long customerId);
}
