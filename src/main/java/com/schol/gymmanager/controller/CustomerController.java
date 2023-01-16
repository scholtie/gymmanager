package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/")
    Customer create(@RequestBody CustomerDto customerDTO) throws EmailExistsException {
        return customerService.create(customerDTO);
    }

    @GetMapping("/{id}")
    Customer findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    Customer update(@RequestBody Customer newUser, @PathVariable Long id) {
        return customerService.update(newUser, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @PostMapping("/subscribe")
    public void subscribe(Long customerId, Long planId) {
        customerService.subscribe(customerId, planId);
    }
}
