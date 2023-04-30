package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.model.progress.RepetitionGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepetitionGoalRepository extends JpaRepository<RepetitionGoal, Long> {

    List<RepetitionGoal> findAllByCustomerId(Long customerId);
}
