package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.CreateSubscriptionRequest;
import com.schol.gymmanager.model.DTOs.DeleteSubscriptionRequest;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.SubscriptionRepository;
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
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private GymRepository gymRepository;

    @GetMapping("/{id}")
    public Subscription findById(@PathVariable long id){
        return subscriptionRepository.findById(id).get();
    }

    @GetMapping("/")
    public List<Subscription> findAll(){
        return subscriptionRepository.findAll();
    }

    @PostMapping("/")
    public Subscription create(@RequestBody CreateSubscriptionRequest createSubscriptionRequest){
        Subscription subscription = new Subscription();
        SubscriptionPlanDto subscriptionPlanDto = createSubscriptionRequest.getSubscriptionPlanDto();
        LocalDateTime startDate = createSubscriptionRequest.getStartDate();
        subscription.setCustomer(createSubscriptionRequest.getCustomer());
        subscription.setOngoing(true);
        subscription.setCurrentPeriodStart(startDate);
        subscription.setPrice(subscriptionPlanDto.getPrice());
        subscription.setCurrentPeriodEnd(startDate.plusDays(subscriptionPlanDto.getDurationInDays()));
        Optional<Gym> gym = gymRepository.findById(subscriptionPlanDto.getGymId());
        gym.ifPresent(subscription::setGym);
        return subscriptionRepository.save(subscription);
    }

    @PostMapping("/{id}")
    public void delete(@PathVariable long subscriptionId) {
        subscriptionRepository.findById(subscriptionId);
    }


}
