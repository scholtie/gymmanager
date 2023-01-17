package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.Subscription;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;
    @Autowired
    private SubscriptionService subscriptionService;

    public List<Customer> findAll() {
        return customerRepository.findAll();
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
        return customerRepository.save(customerToSave);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer", id));
    }

    public Customer update(Customer newUser,Long id) {
        return customerRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setEmail(newUser.getEmail());
                    return customerRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return customerRepository.save(newUser);
                });
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public Boolean emailExist(String email){
        return customerRepository.existsUserAccountByEmail(email);
    }

    public Subscription subscribe(Long customerId, Long planId) {
        Customer customer = findById(customerId);
        SubscriptionPlan plan = subscriptionPlanService.findById(planId);
        Subscription subscription = new Subscription();
        subscription.setGym(plan.getGym());
        subscription.setCustomer(customer);
        subscription.setPrice(plan.getPrice());
        subscription.setOngoing(true);
        subscription.setCancelAtPeriodEnd(false);
        return subscriptionService.create(subscription);
    }
}
