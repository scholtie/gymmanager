package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.UserNotFoundException;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.repository.CustomerRepository;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/")
    List<Customer> findAll() {
        return repository.findAll();
    }

    @PostMapping("/")
    Customer create(@RequestBody CustomerDto customerDTO) throws EmailExistsException {
        if (emailExist(customerDTO.getEmail())) {
            throw new EmailExistsException(customerDTO.getEmail());
        }
        Instant instant = Instant.now();
        Customer customerToSave = new Customer();
        customerToSave.setUserName(customerDTO.getUserName());
        customerToSave.setLastName(customerDTO.getLastName());
        customerToSave.setFirstName(customerDTO.getFirstName());
        customerToSave.setEmail(customerDTO.getEmail());
        customerToSave.setPasswordHash(passwordEncoder.encode(customerDTO.getPassword()));
        customerToSave.setCreateTime(Timestamp.from(instant));
////        if (trainerRepository.findById(customerDTO.getTrainerId()).isPresent()) {
//            customerToSave.setTrainer(trainerRepository.findById(customerDTO.getTrainerId()).get());
//        }
        return repository.save(customerToSave);
    }

    @GetMapping("/{id}")
    Customer findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    Customer update(@RequestBody Customer newUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setEmail(newUser.getEmail());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    public Boolean emailExist(String email){
        return repository.existsUserAccountByEmail(email);
    }

    @PostMapping("/subscribe")
    public void subscribe(Long customerId, Long planId) {

    }
}
