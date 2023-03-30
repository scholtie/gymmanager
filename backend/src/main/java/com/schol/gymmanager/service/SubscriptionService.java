package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.SubscriptionDto;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Subscription;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;
    @Autowired
    private GymService gymService;

    public Subscription findById(long id){
        return subscriptionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Subscription", id));
    }

    public List<Subscription> findAll(){
        return subscriptionRepository.findAll();
    }

    public List<Subscription> findAllByGymId(long gymId) {
        return subscriptionRepository.findAllByGymId(gymId);
    }


    public Subscription create(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription create(SubscriptionDto subscriptionDto){
        Subscription subscription = new Subscription();
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(subscriptionDto.getSubscriptionPlanId());
        //Gym gym = gymService.findById(subscriptionPlan.getGym().getId());
        LocalDate startDate = subscriptionDto.getStartDate();
        subscription.setCustomer(customerService.findById(subscriptionDto.getCustomerId()));
        subscription.setOngoing(true);
        subscription.setCurrentPeriodStart(startDate);
        subscription.setCurrentPeriodEnd(startDate.plusDays(subscriptionPlan.getDurationInDays()));
        subscription.setSubscriptionPlan(subscriptionPlan);
        subscription.setDefaultPaymentMethod(subscriptionDto.getPaymentMethod());
        return create(subscription);
    }

    public void delete(long subscriptionId) {
        subscriptionRepository.findById(subscriptionId);
    }

}
