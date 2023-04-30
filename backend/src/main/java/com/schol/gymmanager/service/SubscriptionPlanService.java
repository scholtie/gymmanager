package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final GymService gymService;
    private final AuthService authService;

    @Autowired
    public SubscriptionPlanService(SubscriptionPlanRepository subscriptionPlanRepository,
                                   GymService gymService,
                                   AuthService authService) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.gymService = gymService;
        this.authService = authService;
    }

    public SubscriptionPlan create(SubscriptionPlanDto subscriptionPlanDto) {
        Gym gym = null;
        if (authService.getLoggedInUser().isPresent()) {
            Role role = authService.getLoggedInUser().get().getRole();
            if (role == Role.GYM) {
                gym = gymService.findByBaseUser(authService.getLoggedInUser().get());
            } else {
                throw new InsufficientRoleException(role);
            }
        }
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .gym(gym)
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
        return subscriptionPlanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SubscriptionPlan", id));
    }

    public List<SubscriptionPlan> findAllByGymId(long id) {
        return subscriptionPlanRepository.findAllByGymId(id);
    }

    public void delete(long id) {
        subscriptionPlanRepository.deleteById(id);
    }
}
