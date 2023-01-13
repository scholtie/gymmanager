package com.schol.gymmanager.controller;

import com.schol.gymmanager.EmailExistsException;
import com.schol.gymmanager.UserNotFoundException;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.repository.TrainerRepository;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TrainerRepository repository;

    @GetMapping("/")
    List<Trainer> findAll() {
        return repository.findAll();
    }

    @PostMapping("/")
    Trainer create(@RequestBody TrainerDto trainerDTO) throws EmailExistsException {
        if (emailExist(trainerDTO.getEmail())) {
            throw new EmailExistsException(trainerDTO.getEmail());
        }
        Instant instant = Instant.now();
        Trainer trainerToSave = new Trainer();
        trainerToSave.setUserName(trainerDTO.getUserName());
        trainerToSave.setLastName(trainerDTO.getLastName());
        trainerToSave.setFirstName(trainerDTO.getFirstName());
        trainerToSave.setEmail(trainerDTO.getEmail());
        trainerToSave.setPasswordHash(passwordEncoder.encode(trainerDTO.getPassword()));
        trainerToSave.setCreateTime(Timestamp.from(instant));
        return repository.save(trainerToSave);
    }

    @GetMapping("/{id}")
    Trainer findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    Trainer update(@RequestBody Trainer newTrainer, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setUserName(newTrainer.getUserName());
                    user.setEmail(newTrainer.getEmail());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newTrainer.setId(id);
                    return repository.save(newTrainer);
                });
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    public Boolean emailExist(String email){
        return repository.existsUserAccountByEmail(email);
    }
}
