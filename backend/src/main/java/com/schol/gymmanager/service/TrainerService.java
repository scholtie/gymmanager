package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.user.Trainer;
import com.schol.gymmanager.model.enums.Gender;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final PasswordEncoder passwordEncoder;
    private final TrainerRepository trainerRepository;
    private final SessionRepository sessionRepository;
    private final GymService gymService;
    private final AuthService authService;

    @Autowired
    public TrainerService(PasswordEncoder passwordEncoder, TrainerRepository trainerRepository,
                          SessionRepository sessionRepository, GymService gymService, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.trainerRepository = trainerRepository;
        this.sessionRepository = sessionRepository;
        this.gymService = gymService;
        this.authService = authService;
    }

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer create(TrainerDto trainerDTO) throws EmailExistsException {
        Trainer trainerToSave = new Trainer();
        trainerToSave.setLastName(trainerDTO.getLastName());
        trainerToSave.setFirstName(trainerDTO.getFirstName());
        if (authService.getLoggedInUser().isPresent()) {
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
                .map(trainer -> {
                    // trainer.setEmail(newTrainer.getEmail());
                    return trainerRepository.save(trainer);
                })
                .orElseGet(() -> {
                    newTrainer.setId(id);
                    return trainerRepository.save(newTrainer);
                });
    }

    public void delete(Long id) {
        trainerRepository.deleteById(id);
    }

    public List<Customer> findCustomersOfTrainer() {
        Optional<Role> loggedInUserRole = authService.getLoggedInUser().map(BaseUser::getRole);
        Trainer trainer;
        if (loggedInUserRole.isPresent()) {
            Role role = loggedInUserRole.get();
            if (role == Role.TRAINER) {
                trainer = findByBaseUser(authService.getLoggedInUser().get());
            }else {
                throw new InsufficientRoleException(role);
            }
        } else {
            trainer = null;
        }
        return sessionRepository.findAll().stream()
                .filter(session -> {
                    assert trainer != null;
                    return session.getTrainer().getId() == trainer.getId();
                })
                .map(Session::getCustomer)
                .collect(Collectors.toList());
    }
}

