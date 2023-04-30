package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.exception.SubscriptionException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.SubscriptionDto;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private AuthService authService;

    public Subscription findById(long id){
        return subscriptionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Subscription", id));
    }

    public List<Subscription> findAll(){
        return subscriptionRepository.findAll();
    }

    public List<Subscription> findAllOngoingByLoggedInUser() {
        if (authService.getLoggedInUser().isPresent()) {
            Role role = authService.getLoggedInUser().get().getRole();
            if (role == Role.CUSTOMER)  {
                Customer customer = customerService.findByBaseUser(authService.getLoggedInUser().get());
                return subscriptionRepository.findAllByCustomerId(customer.getId());
            } else if (role == Role.GYM) {
                Gym gym = gymService.findByBaseUser(authService.getLoggedInUser().get());
                return subscriptionRepository.findAllByGymId(gym.getId());
            }
            else{throw new InsufficientRoleException(role);
            }
        }
        else {return new ArrayList<>();}
    }


    public Subscription create(Subscription subscription) {
            return subscriptionRepository.save(subscription);
    }

    public Subscription create(SubscriptionDto subscriptionDto){
        if (findAllOngoingByLoggedInUser().isEmpty()) {
            Subscription subscription = new Subscription();
            SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(subscriptionDto.getSubscriptionPlanId());
            LocalDate startDate = subscriptionDto.getStartDate();
            if (customerService.getLoggedInCustomer().isPresent()) {
                subscription.setCustomer(customerService.getLoggedInCustomer().get());
            } else {
                throw new InsufficientRoleException();
            }
            subscription.setOngoing(true);
            subscription.setCurrentPeriodStart(startDate);
            subscription.setCurrentPeriodEnd(startDate.plusDays(subscriptionPlan.getDurationInDays()));
            subscription.setSubscriptionPlan(subscriptionPlan);
            subscription.setDefaultPaymentMethod(subscriptionDto.getPaymentMethod());
            return create(subscription);
        }
        else{throw new SubscriptionException();
        }
    }

    public void delete(long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

}
