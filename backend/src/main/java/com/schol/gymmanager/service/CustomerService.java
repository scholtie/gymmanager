package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.repository.BaseUserRepository;
import com.schol.gymmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private BaseUserRepository baseUserRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(CustomerDto customerDTO) throws EmailExistsException {
        Customer customerToSave = new Customer();
        customerToSave.setGender(Gender.valueOf(customerDTO.getGender()));
        customerToSave.setLastName(customerDTO.getLastName());
        customerToSave.setFirstName(customerDTO.getFirstName());
        if (authService.getLoggedInUser().isPresent()){
            customerToSave.setBaseUser(authService.getLoggedInUser().get());
        }
////        if (trainerRepository.findById(customerDTO.getTrainerId()).isPresent()) {
//            customerToSave.setTrainer(trainerRepository.findById(customerDTO.getTrainerId()).get());
//        }
        return customerRepository.save(customerToSave);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer", id));
    }

    public Customer update(Customer newUser,Long id) {
        return customerRepository.findById(id)
                .map(user -> {
                    //user.setEmail(newUser.getEmail());
                    return customerRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return customerRepository.save(newUser);
                });
    }

    public Optional<Customer> getLoggedInCustomer(){
        return customerRepository.findByBaseUser(authService.getLoggedInUser().get());
    }



    public void delete(Long id) {
        customerRepository.deleteById(id);
    }}
