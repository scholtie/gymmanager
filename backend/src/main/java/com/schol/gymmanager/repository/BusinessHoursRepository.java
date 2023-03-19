package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
}
