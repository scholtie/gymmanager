package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.BaseUser;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.model.progress.RepetitionGoal;
import com.schol.gymmanager.repository.RepetitionGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RepetitionGoalService {
    private final RepetitionGoalRepository repository;
    private final AuthService authService;
    private final CustomerService customerService;

    @Autowired
    public RepetitionGoalService(RepetitionGoalRepository repository, AuthService authService, CustomerService customerService) {
        this.repository = repository;
        this.authService = authService;
        this.customerService = customerService;
    }

    public List<RepetitionGoal> getAll() {
        return repository.findAll();
    }

    public RepetitionGoal getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Repetition goal", id));
    }

    public List<RepetitionGoal> getAllForLoggedInCustomer() {
        Customer customer = getLoggedInCustomer();
        if (customer == null) {
            return Collections.emptyList();
        }
        return repository.findAllByCustomerId(customer.getId());
    }

    public List<RepetitionGoal> getByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }

    public RepetitionGoal create(RepetitionGoal repetitionGoal) {
        Customer customer = getLoggedInCustomer();
        if (customer == null) {
            throw new InsufficientRoleException();
        }
        RepetitionGoal goal = RepetitionGoal.builder()
                .customer(customer)
                .value(repetitionGoal.getValue())
                .date(repetitionGoal.getDate())
                .name(repetitionGoal.getName())
                .repetitions(repetitionGoal.getRepetitions())
                .build();
        return repository.save(goal);
    }

    private Customer getLoggedInCustomer() {
        BaseUser user = authService.getLoggedInUser().orElseThrow(() ->
                new AccessDeniedException("User is not logged in"));
        if (user.getRole() != Role.CUSTOMER) {
            throw new InsufficientRoleException(user.getRole());
        }
        return customerService.findByBaseUser(user);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}

