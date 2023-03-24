package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping(value = "/subscriptions")
@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    public Subscription findById(@PathVariable long id){
        Subscription subscription = subscriptionService.findById(id);
        addLinks(subscription);
        return subscription;}

    @GetMapping("/")
    public List<Subscription> findAll(){
        List<Subscription> subscriptions = subscriptionService.findAll();
        for (Subscription subscription : subscriptions) {
            addLinks(subscription);
        }
        return subscriptions;
    }

    @PostMapping("/")
    public Subscription create(@RequestBody SubscriptionPlanDto subscriptionPlanDto){
        Subscription subscription = subscriptionService.create(subscriptionPlanDto);
        addLinks(subscription);
        return subscription;
    }

    @PostMapping("/{id}")
    public void delete(@PathVariable long id) {
        subscriptionService.delete(id);
    }

    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody SubscriptionPlanDto subscriptionPlanDto) {
        Subscription subscription = subscriptionService.create(subscriptionPlanDto);
        addLinks(subscription);
        return subscription;
    }

    private void addLinks(Subscription subscription) {
        if ( subscription != null ) {
            subscription.add(linkTo(methodOn(SubscriptionController.class).findById(subscription.getId())).withSelfRel());

            if ( subscription.getGym() != null ) {
                subscription.add(linkTo(methodOn(GymController.class).findById(subscription.getGym().getId())).withRel("gym"));
            }

            if ( subscription.getCustomer() != null ) {
                subscription.add(linkTo(methodOn(SubscriptionController.class).findById(subscription.getCustomer().getId())).withRel("customer"));
            }
        }
    }
}
