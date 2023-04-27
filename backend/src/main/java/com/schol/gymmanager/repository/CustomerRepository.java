package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BaseUser;
import com.schol.gymmanager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByBaseUser(BaseUser baseUser);
}
