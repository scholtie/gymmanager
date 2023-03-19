package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Price;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
