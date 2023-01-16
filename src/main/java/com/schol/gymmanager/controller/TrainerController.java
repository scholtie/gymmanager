package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.repository.TrainerRepository;
import com.schol.gymmanager.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/trainers")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/")
    public List<Trainer> findAll() {
        return trainerService.findAll();
    }

    @PostMapping("/")
    public Trainer create(@RequestBody TrainerDto trainerDTO) throws EmailExistsException {
        return trainerService.create(trainerDTO);
    }

    @GetMapping("/{id}")
    public Trainer findById(@PathVariable Long id) {
        return trainerService.findById(id);
    }

    @PutMapping("/{id}")
    public Trainer update(@RequestBody Trainer newTrainer, @PathVariable Long id) {
        return trainerService.update(newTrainer, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trainerService.delete(id);
    }

    public Boolean emailExist(String email){
        return trainerService.emailExist(email);
    }
}
