package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.exception.SubscriptionException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.SubscriptionDto;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.SubscriptionRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CustomerService customerService;
    private final SubscriptionPlanService subscriptionPlanService;
    private final GymService gymService;
    private final AuthService authService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                               CustomerService customerService,
                               SubscriptionPlanService subscriptionPlanService,
                               GymService gymService,
                               AuthService authService) {
        this.subscriptionRepository = subscriptionRepository;
        this.customerService = customerService;
        this.subscriptionPlanService = subscriptionPlanService;
        this.gymService = gymService;
        this.authService = authService;
    }

    public Subscription findById(long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", id));
    }

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> findAllOngoingByLoggedInUser() {
        Optional<BaseUser> loggedInUser = authService.getLoggedInUser();
        if (loggedInUser.isPresent()) {
            Role role = loggedInUser.get().getRole();
            if (role == Role.CUSTOMER) {
                Customer customer = customerService.findByBaseUser(loggedInUser.get());
                return subscriptionRepository.findAllByCustomerId(customer.getId());
            } else if (role == Role.GYM) {
                Gym gym = gymService.findByBaseUser(loggedInUser.get());
                return subscriptionRepository.findAllByGymId(gym.getId());
            } else {
                throw new InsufficientRoleException(role);
            }
        }
        return new ArrayList<>();
    }

    public Subscription create(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription create(SubscriptionDto subscriptionDto) {
        List<Subscription> ongoingSubscriptions = findAllOngoingByLoggedInUser();
        Long subId = subscriptionDto.getId();
        if (Objects.isNull(subId)) {
            if (ongoingSubscriptions.isEmpty()) {
                Subscription subscription = new Subscription();
                SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(subscriptionDto.getSubscriptionPlanId());
                LocalDate startDate = subscriptionDto.getStartDate();
                Optional<Customer> loggedInCustomer = customerService.getLoggedInCustomer();
                if (loggedInCustomer.isPresent()) {
                    subscription.setCustomer(loggedInCustomer.get());
                } else {
                    throw new InsufficientRoleException();
                }
                if (subscriptionDto.isCancelAtPeriodEnd()) {
                    subscription.setCancelAtPeriodEnd(true);
                    scheduleSubscriptionCancellation(subscription);
                }
                subscription.setCancelAtPeriodEnd(subscriptionDto.isCancelAtPeriodEnd());
                subscription.setOngoing(true);
                subscription.setCurrentPeriodStart(startDate);
                subscription.setCurrentPeriodEnd(startDate.plusDays(subscriptionPlan.getDurationInDays()));
                subscription.setSubscriptionPlan(subscriptionPlan);
                subscription.setDefaultPaymentMethod(subscriptionDto.getPaymentMethod());
                return create(subscription);
            } else {
                throw new SubscriptionException();
            }
        }
        else{
            Subscription subscription = findById(subId);
            subscription.setCancelAtPeriodEnd(subscriptionDto.isCancelAtPeriodEnd());
            return create(subscription);
        }
    }

    public void cancelAtPeriodEnd(long id){
        SubscriptionDto subscription = new SubscriptionDto();
        subscription.setId(id);
        subscription.setCancelAtPeriodEnd(true);
        create(subscription);
    }

    public void delete(long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    private void scheduleSubscriptionCancellation(Subscription subscription) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                delete(subscription.getId());
            }
        }, Date.from(subscription.getCurrentPeriodEnd().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

}
