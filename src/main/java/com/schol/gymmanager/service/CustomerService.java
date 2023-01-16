package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer create(CustomerDto customerDTO) throws EmailExistsException {
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

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer", id));
    }

    public Customer update(Customer newUser,Long id) {
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

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Boolean emailExist(String email){
        return repository.existsUserAccountByEmail(email);
    }

    public void subscribe(Long customerId, Long planId) {

    }
}
