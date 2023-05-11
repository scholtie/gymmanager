package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.user.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionOptionRepository extends JpaRepository<SessionOption, Long> {
    List<SessionOption> findAllByTrainer(Trainer trainer);
}
