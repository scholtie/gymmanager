package com.schol.gymmanager.controller;


import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/gyms")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping("/")
    public List<Gym> findAll() {
        return gymService.findAll();
    }

    @GetMapping("/{id}")
    public Gym findById(@PathVariable long id) {
        return gymService.findById(id);
    }
}
