package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Subscription;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private GymService gymService;

    public Subscription findById(long id){
        return subscriptionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Subscription", id));
    }

    public List<Subscription> findAll(){
        return subscriptionRepository.findAll();
    }

    public Subscription create(SubscriptionPlanDto subscriptionPlanDto){
        Subscription subscription = new Subscription();
        LocalDateTime startDate = subscriptionPlanDto.getStartDate();
        subscription.setCustomer(subscriptionPlanDto.getCustomer());
        subscription.setOngoing(true);
        subscription.setCurrentPeriodStart(startDate);
        subscription.setPrice(subscriptionPlanDto.getPrice());
        subscription.setCurrentPeriodEnd(startDate.plusDays(subscriptionPlanDto.getDurationInDays()));
        subscription.setGym(gymService.findById(subscriptionPlanDto.getGymId()));
        return subscriptionRepository.save(subscription);
    }

    public void delete(long subscriptionId) {
        subscriptionRepository.findById(subscriptionId);
    }

}
