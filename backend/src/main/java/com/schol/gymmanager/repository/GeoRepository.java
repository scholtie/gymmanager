package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Geo;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoRepository extends JpaRepository<Geo, Long> {
}
