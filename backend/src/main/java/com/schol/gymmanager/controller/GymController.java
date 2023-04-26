package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.DTOs.BusinessHoursDto;
import com.schol.gymmanager.model.DTOs.GymDto;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/gyms")
public class GymController {

    @Autowired
    private GymService gymService;

    @GetMapping("/")
    public List<Gym> findAll() {
        List<Gym> gyms = gymService.findAll();
        for (Gym gym : gyms) {
            addLinks(gym);
        }
        return gyms;
    }

    @GetMapping("/{id}")
    public Gym findById(@PathVariable long id) {
        Gym gym = gymService.findById(id);
        addLinks(gym);
        return gym;
    }

    @PostMapping("/")
    public Gym create(@RequestBody GymDto gymDTO) {
        Gym gym = gymService.create(gymDTO);
        addLinks(gym);
        return gym;
    }

    @PostMapping("/setBusinessHours/")
    public BusinessHours setBusinessHours(@RequestBody BusinessHoursDto businessHoursDto){
        return gymService.setBusinessHours(businessHoursDto);
    }

    @GetMapping("/businessHours/{id}")
    public List<BusinessHours> findBusinessHoursForGym(@PathVariable long id){
        return gymService.findBusinessHoursForGym(id);
    }

    private void addLinks(Gym gym) {
        gym.add(linkTo(methodOn(GymController.class).findById(gym.getId())).withSelfRel());
    }
}
