package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionOptionRepository extends JpaRepository<SessionOption, Long> {
}
