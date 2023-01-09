package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriptionPlanController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    public SubscriptionPlan create(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanService.create(subscriptionPlan);
    }

    public List<SubscriptionPlan> findAll() {
        return subscriptionPlanService.findAll();
    }

}
