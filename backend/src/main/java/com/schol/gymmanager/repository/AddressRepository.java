package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Address;
import com.schol.gymmanager.model.SessionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
