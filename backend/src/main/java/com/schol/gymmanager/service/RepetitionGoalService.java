package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.model.progress.RepetitionGoal;
import com.schol.gymmanager.repository.RepetitionGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepetitionGoalService {
    @Autowired
    private RepetitionGoalRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerService customerService;

    public List<RepetitionGoal> findAll() {
        return repository.findAll();
    }

    public RepetitionGoal findById(Long id) {
        return repository.findById(id).get();
    }
    public List<RepetitionGoal> getAllForLoggedInCustomer() {
        if (authService.getLoggedInUser().isPresent()) {
            Role role = authService.getLoggedInUser().get().getRole();
            if (role == Role.CUSTOMER)  {
                Customer customer = customerService.findByBaseUser(authService.getLoggedInUser().get());
                return repository.findAllByCustomerId(customer.getId());
            }
            else{throw new InsufficientRoleException(role);
            }
        }
        return new ArrayList<>();
    }

    public List<RepetitionGoal> findByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }

    public RepetitionGoal create(RepetitionGoal repetitionGoal) {
        Customer customer = null;
        if (authService.getLoggedInUser().isPresent()) {
            Role role = authService.getLoggedInUser().get().getRole();
            if (role == Role.CUSTOMER)  {
                customer = customerService.findByBaseUser(authService.getLoggedInUser().get());
            }
            else{throw new InsufficientRoleException(role);
            }
        }
        RepetitionGoal repetitionGoalToSave = RepetitionGoal.builder().customer(customer)
                .value(repetitionGoal.getValue())
                .date(repetitionGoal.getDate())
                .name(repetitionGoal.getName())
                .repetitions(repetitionGoal.getRepetitions())
                .build();
        return repository.save(repetitionGoalToSave);
    }
}
