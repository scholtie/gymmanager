package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.DeleteSubscriptionRequest;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.SubscriptionRepository;
import com.schol.gymmanager.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/subscriptions")
@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    public Subscription findById(@PathVariable long id){
        return subscriptionService.findById(id);}

    @GetMapping("/")
    public List<Subscription> findAll(){
        return subscriptionService.findAll();
    }

    @PostMapping("/")
    public Subscription create(@RequestBody SubscriptionPlanDto subscriptionPlanDto){
        return subscriptionService.create(subscriptionPlanDto);
    }

    @PostMapping("/{id}")
    public void delete(@PathVariable long subscriptionId) {
        subscriptionService.delete(subscriptionId);
    }

    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody SubscriptionPlanDto subscriptionPlanDto) {
        return subscriptionService.create(subscriptionPlanDto);
    }


}
