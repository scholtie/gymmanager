package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.user.Trainer;
import com.schol.gymmanager.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/trainers")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/")
    public List<Trainer> findAll() {
        List<Trainer> trainers = trainerService.findAll();
        for ( Trainer trainer : trainers) {
            addLinks(trainer);
        }
        return trainers;
    }

    @PostMapping("/")
    public Trainer create(@RequestBody TrainerDto trainerDTO) throws EmailExistsException {
        Trainer trainer = trainerService.create(trainerDTO);
        addLinks(trainer);
        return trainer;
    }

    @GetMapping("/{id}")
    public Trainer findById(@PathVariable Long id) {
        Trainer trainer = trainerService.findById(id);
        addLinks(trainer);
        return trainer;
    }

    @PutMapping("/{id}")
    public Trainer update(@RequestBody Trainer newTrainer, @PathVariable Long id) {
        Trainer trainer = trainerService.update(newTrainer, id);
        addLinks(trainer);
        return trainer;
    }

    @GetMapping("/customers")
    public List<Customer> findCustomersOfTrainer() {
        return trainerService.findCustomersOfTrainer();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trainerService.delete(id);
    }

    private void addLinks(Trainer trainer) {
        if ( trainer != null) {
            trainer.add(linkTo(methodOn(TrainerController.class).findById(trainer.getId())).withSelfRel());

            if( trainer.getGender() != null) {
                trainer.add(linkTo(methodOn(GymController.class).findById(trainer.getGym().getId())).withRel("gym"));
            }
        }
    }
}
