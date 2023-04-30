package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.NumericGoalDto;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.repository.NumericGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumericGoalService {
    @Autowired
    private NumericGoalRepository numericGoalRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerService customerService;

    public NumericGoal findById(Long id) {
        return numericGoalRepository.findById(id).get();
    }

    public List<NumericGoal> findAll() {
        return numericGoalRepository.findAll();
    }

    public List<NumericGoal> findByCustomerId(Long customerId) {
        return numericGoalRepository.findAllByCustomerId(customerId);
    }

    public List<NumericGoal> getAllForLoggedInCustomer() {
        if (authService.getLoggedInUser().isPresent()) {
            Role role = authService.getLoggedInUser().get().getRole();
            if (role == Role.CUSTOMER)  {
                Customer customer = customerService.findByBaseUser(authService.getLoggedInUser().get());
                return numericGoalRepository.findAllByCustomerId(customer.getId());
            }
            else{throw new InsufficientRoleException(role);
            }
        }
        return new ArrayList<>();
    }

    public NumericGoal create(NumericGoalDto numericGoal) {
        Customer customer = null;
        if (authService.getLoggedInUser().isPresent()) {
            Role role = authService.getLoggedInUser().get().getRole();
            if (role == Role.CUSTOMER)  {
                customer = customerService.findByBaseUser(authService.getLoggedInUser().get());
            }
            else{throw new InsufficientRoleException(role);
            }
        }
        NumericGoal numericGoalToSave = NumericGoal.builder().customer(customer)
                .value(numericGoal.getValue())
                .date(numericGoal.getDate())
                .name(numericGoal.getName()).build();
        return numericGoalRepository.save(numericGoalToSave);
    }
}
