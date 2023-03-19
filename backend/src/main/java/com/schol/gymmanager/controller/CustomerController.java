package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/")
    public Customer create(@RequestBody CustomerDto customerDTO) throws EmailExistsException {
        return customerService.create(customerDTO);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer newUser, @PathVariable Long id) {
        return customerService.update(newUser, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
