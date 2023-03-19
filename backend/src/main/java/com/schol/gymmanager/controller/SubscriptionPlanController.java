package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subscriptionplans")
public class SubscriptionPlanController {
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @PostMapping("/")
    public SubscriptionPlan create(@RequestBody SubscriptionPlanDto subscriptionPlanDto) {
        return subscriptionPlanService.create(subscriptionPlanDto);
    }

    @GetMapping("/")
    public List<SubscriptionPlan> findAll() {
        return subscriptionPlanService.findAll();
    }

    @GetMapping("/{id}")
    public SubscriptionPlan findById(@PathVariable long id) {
        return subscriptionPlanService.findById(id);}

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        subscriptionPlanService.delete(id);
    }

}
