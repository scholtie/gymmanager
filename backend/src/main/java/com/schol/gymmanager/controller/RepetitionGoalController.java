package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.model.progress.RepetitionGoal;
import com.schol.gymmanager.service.RepetitionGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/repetitiongoal")
public class RepetitionGoalController {
    
    @Autowired
    private RepetitionGoalService repetitionGoalService;
    
    @GetMapping("/{id}")
    public RepetitionGoal findById(@PathVariable Long id) {
        return repetitionGoalService.findById(id);
    }
    
    @PostMapping("/")
    public RepetitionGoal create(@RequestBody RepetitionGoal goal) {
        return repetitionGoalService.create(goal);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<RepetitionGoal> findByCustomerId(@PathVariable Long customerId) {
        return repetitionGoalService.findByCustomerId(customerId);
    }

    @GetMapping("/getForLoggedInCustomer")
    public List<RepetitionGoal> getAllForLoggedInCustomer() {
        return repetitionGoalService.getAllForLoggedInCustomer();
    }

}