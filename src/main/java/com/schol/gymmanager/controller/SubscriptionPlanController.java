package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subscriptionplans")
public class SubscriptionPlanController {
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private GymRepository gymRepository;

    @PostMapping("/")
    public SubscriptionPlan create(@RequestBody SubscriptionPlanDto subscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .gym(gymRepository.findById(subscriptionPlanDto.getGymId()).get())
                .description(subscriptionPlanDto.getDescription())
                .durationInDays(subscriptionPlanDto.getDurationInDays())
                .price(subscriptionPlanDto.getPrice())
                .name(subscriptionPlanDto.getName())
                .build();
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @GetMapping("/")
    public List<SubscriptionPlan> findAll() {
        return subscriptionPlanRepository.findAll();
    }

    @GetMapping("/{id}")
    public SubscriptionPlan findById(@PathVariable long id) {
        return subscriptionPlanRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        subscriptionPlanRepository.deleteById(id);
    }

}
