package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/subscriptionplans")
public class SubscriptionPlanController {
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @PostMapping("/")
    public SubscriptionPlan create(@RequestBody SubscriptionPlanDto subscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.create(subscriptionPlanDto);
        addLinks(subscriptionPlan);
        return subscriptionPlan;
    }

    @GetMapping("/")
    public List<SubscriptionPlan> findAll() {
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanService.findAll();
        for (SubscriptionPlan subscriptionPlan : subscriptionPlans) {
            addLinks(subscriptionPlan);
        }
        return subscriptionPlans;
    }

    @GetMapping("/{id}")
    public SubscriptionPlan findById(@PathVariable long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(id);
        addLinks(subscriptionPlan);
        return subscriptionPlan;
    }

    @GetMapping("/findByGym/{gymId}")
    public List<SubscriptionPlan> findByGymId(@PathVariable long gymId) {
        //addLinks(subscriptionPlans);
        return subscriptionPlanService.findAllByGymId(gymId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        subscriptionPlanService.delete(id);
    }

    private void addLinks(SubscriptionPlan subscriptionPlan) {
        if ( subscriptionPlan != null) {
            subscriptionPlan.add(linkTo(methodOn(SubscriptionPlanController.class).findById(subscriptionPlan.getId())).withSelfRel());

//            if ( subscriptionPlan.getCustomer() != null) {
//                subscriptionPlan.add(linkTo(methodOn(CustomerController.class).findById(subscriptionPlan.getCustomer().getId())).withRel("customer"));
//            }

            if ( subscriptionPlan.getGym() != null) {
                subscriptionPlan.add(linkTo(methodOn(GymController.class).findById(subscriptionPlan.getGym().getId())).withRel("gym"));
            }
        }
    }

}
