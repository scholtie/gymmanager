package com.schol.gymmanager.controller;


import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.SubscriptionPlan;
import com.schol.gymmanager.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/gyms")
public class GymController {

    @Autowired
    private GymRepository gymRepository;

    @GetMapping("/")
    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    @GetMapping("/{id}")
    public Gym findById(@PathVariable long id) {
        return gymRepository.findById(id).get();
    }
}
