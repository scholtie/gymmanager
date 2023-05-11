package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.DTOs.NumericGoalDto;
import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.service.NumericGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/numericgoal")
public class NumericGoalController {

    @Autowired
    private NumericGoalService numericGoalService;

    @GetMapping("/{id}")
    public NumericGoal findById(@PathVariable Long id) {
        return numericGoalService.findById(id);
    }

    @GetMapping("/")
    public List<NumericGoal> findAll() {
        return numericGoalService.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public List<NumericGoal> findByCustomerId(@PathVariable Long customerId) {
        return numericGoalService.findByCustomerId(customerId);
    }

    @GetMapping("/getForLoggedInCustomer")
    public List<NumericGoal> getAllForLoggedInCustomer() {
        return numericGoalService.getAllForLoggedInCustomer();
    }

    @PostMapping("/")
    public NumericGoal create(@RequestBody NumericGoalDto goal) {
        return numericGoalService.create(goal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        numericGoalService.delete(id);
    }
}
