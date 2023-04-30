package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.enums.Gender;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private GymService gymService;
    @Autowired
    private AuthService authService;

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer create(TrainerDto trainerDTO) throws EmailExistsException {
        Trainer trainerToSave = new Trainer();
        trainerToSave.setLastName(trainerDTO.getLastName());
        trainerToSave.setFirstName(trainerDTO.getFirstName());
        if (authService.getLoggedInUser().isPresent()){
            trainerToSave.setBaseUser(authService.getLoggedInUser().get());
        }
        trainerToSave.setGym(gymService.findById(trainerDTO.getGymId()));
        trainerToSave.setGender(Gender.valueOf(trainerDTO.getGender()));
        trainerToSave.setIntroduction(trainerDTO.getIntroduction());
        trainerToSave.setImgPath(trainerDTO.getImgPath());
        return trainerRepository.save(trainerToSave);
    }

    public Trainer findById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer", id));
    }

    public Trainer findByBaseUser(BaseUser baseUser) {
        return trainerRepository.findByBaseUser(baseUser);
    }

    public Trainer update(Trainer newTrainer, Long id) {
        return trainerRepository.findById(id)
                .map(user -> {
                    //user.setEmail(newTrainer.getEmail());
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

    public List<Customer> findCustomersOfTrainer(Long trainerId) {
        return sessionRepository.findAll().stream()
                .filter(session -> session.getTrainer().getId() == trainerId)
                .map(Session::getCustomer)
                .collect(Collectors.toList());
    }
}
