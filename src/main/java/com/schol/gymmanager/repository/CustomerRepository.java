package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsUserAccountByEmail (String email);
}
