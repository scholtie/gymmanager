package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.BaseUser;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.NumericGoalDto;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.repository.NumericGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NumericGoalService {
    private final NumericGoalRepository numericGoalRepository;
    private final AuthService authService;
    private final CustomerService customerService;
    @Autowired
    public NumericGoalService(NumericGoalRepository numericGoalRepository,
                              AuthService authService,
                              CustomerService customerService) {
        this.numericGoalRepository = numericGoalRepository;
        this.authService = authService;
        this.customerService = customerService;
    }

    public NumericGoal findById(Long id) {
        return numericGoalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NumericGoal not found"));
    }

    public List<NumericGoal> findAll() {
        return numericGoalRepository.findAll();
    }

    public List<NumericGoal> findByCustomerId(Long customerId) {
        return numericGoalRepository.findAllByCustomerId(customerId);
    }

    public List<NumericGoal> getAllForLoggedInCustomer() {
        BaseUser loggedInUser = authService.getLoggedInUser()
                .orElseThrow(() -> new IllegalStateException("User not logged in"));
        if (loggedInUser.getRole() != Role.CUSTOMER) {
            throw new InsufficientRoleException(loggedInUser.getRole());
        }
        Customer customer = customerService.findByBaseUser(loggedInUser);
        return numericGoalRepository.findAllByCustomerId(customer.getId());
    }

    public NumericGoal create(NumericGoalDto numericGoal) {
        Customer customer = null;
        BaseUser loggedInUser = authService.getLoggedInUser()
                .orElseThrow(() -> new IllegalStateException("User not logged in"));
        if (loggedInUser.getRole() != Role.CUSTOMER) {
            throw new InsufficientRoleException(loggedInUser.getRole());
        }
        customer = customerService.findByBaseUser(loggedInUser);
        NumericGoal numericGoalToSave = NumericGoal.builder()
                .customer(customer)
                .value(numericGoal.getValue())
                .date(numericGoal.getDate())
                .name(numericGoal.getName())
                .build();
        return numericGoalRepository.save(numericGoalToSave);
    }
}