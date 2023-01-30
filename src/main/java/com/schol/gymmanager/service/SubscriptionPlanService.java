package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private GymService gymService;

    public SubscriptionPlan create(SubscriptionPlanDto subscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .gym(gymService.findById(subscriptionPlanDto.getGymId()))
                .description(subscriptionPlanDto.getDescription())
                .durationInDays(subscriptionPlanDto.getDurationInDays())
                .price(subscriptionPlanDto.getPrice())
                .name(subscriptionPlanDto.getName())
                .build();
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    public List<SubscriptionPlan> findAll() {
        return subscriptionPlanRepository.findAll();
    }

    public SubscriptionPlan findById(long id) {
        return subscriptionPlanRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("SubscriptionPlan", id));
    }

    public void delete(long id) {
        subscriptionPlanRepository.deleteById(id);
    }

}
