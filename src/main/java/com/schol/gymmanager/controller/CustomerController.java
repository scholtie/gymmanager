package com.schol.gymmanager.controller;

import com.schol.gymmanager.EmailExistsException;
import com.schol.gymmanager.UserNotFoundException;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final CustomerRepository repository;
    private final TrainerRepository trainerRepository;

    CustomerController(CustomerRepository repository, TrainerRepository trainerRepository) {
        this.repository = repository;
        this.trainerRepository = trainerRepository;
    }

    @GetMapping("/customers")
    List<Customer> all() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    Customer newCustomer (@RequestBody CustomerDto customerDTO) throws EmailExistsException {
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
//        if (trainerRepository.findById(customerDTO.getTrainerId()).isPresent()) {
//            customerToSave.setTrainer(trainerRepository.findById(customerDTO.getTrainerId()).get());
//        }
        return repository.save(customerToSave);
    }

    @GetMapping("/customers/{id}")
    Customer one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/customers/{id}")
    Customer replaceCustomer (@RequestBody Customer newUser, @PathVariable Long id) {
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

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
    }

    public Boolean emailExist(String email){
        return repository.existsUserAccountByEmail(email);
    }

    @PostMapping("/customers/subscribe")
    public void subscribe(Long customerId, Long planId) {

    }
}
