package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.CreateSubscriptionRequest;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequestMapping(value = "/subscriptions")
@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    GymRepository gymRepository;


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

    public void cancel(Customer customer, SubscriptionPlan plan) {

    }


}
