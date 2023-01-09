package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
