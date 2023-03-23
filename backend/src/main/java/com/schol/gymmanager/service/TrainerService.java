package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private GymService gymService;

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer create(TrainerDto trainerDTO) throws EmailExistsException {
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
        trainerToSave.setGym(gymService.findById(trainerDTO.getGymId()));
        return trainerRepository.save(trainerToSave);
    }

    public Trainer findById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer", id));
    }

    public Trainer update(Trainer newTrainer, Long id) {
        return trainerRepository.findById(id)
                .map(user -> {
                    user.setUserName(newTrainer.getUserName());
                    user.setEmail(newTrainer.getEmail());
                    return trainerRepository.save(user);
                })
                .orElseGet(() -> {
                    newTrainer.setId(id);
                    return trainerRepository.save(newTrainer);
                });
    }

    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }

    public Boolean emailExist(String email){
        return trainerRepository.existsUserAccountByEmail(email);
    }

}