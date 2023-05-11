package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.DTOs.SessionOptionDto;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.user.Trainer;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.SessionOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SessionOptionService {
    private final SessionOptionRepository sessionOptionRepository;
    private final TrainerService trainerService;
    private final AuthService authService;

    @Autowired
    public SessionOptionService(SessionOptionRepository sessionOptionRepository, TrainerService trainerService, AuthService authService) {
        this.sessionOptionRepository = sessionOptionRepository;
        this.trainerService = trainerService;
        this.authService = authService;
    }

    public SessionOption findById(long id) {
        return sessionOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SessionOption", id));
    }

    public List<SessionOption> findAll() {
        return sessionOptionRepository.findAll();
    }

    public List<SessionOption> findAllByLoggedInTrainer() {
        Optional<BaseUser> optionalLoggedInUser = authService.getLoggedInUser();
        if (optionalLoggedInUser.isPresent()) {
            BaseUser loggedInUser = optionalLoggedInUser.get();
            Role role = loggedInUser.getRole();
            if (role == Role.TRAINER) {
                Trainer trainer = trainerService.findByBaseUser(loggedInUser);
                return sessionOptionRepository.findAllByTrainer(trainer);
            } else {
                throw new InsufficientRoleException(role);
            }
        } else {
            return null;
        }
    }

    public List<SessionOption> findAllByTrainer(long trainerId) {
        Trainer trainer = trainerService.findById(trainerId);
        return sessionOptionRepository.findAllByTrainer(trainer);
    }

    public SessionOption create(SessionOptionDto sessionOptionDto) {
        SessionOption sessionOption = new SessionOption();
        if (Objects.nonNull(sessionOptionDto.getId())){
            sessionOption.setId(sessionOptionDto.getId());
        }
        sessionOption.setName(sessionOptionDto.getName());
        sessionOption.setPrice(sessionOptionDto.getPrice());
        sessionOption.setDescription(sessionOptionDto.getDescription());
        sessionOption.setLengthMinutes(sessionOptionDto.getLengthMinutes());

        BaseUser loggedInUser = authService.getLoggedInUser()
                .orElseThrow(InsufficientRoleException::new);

        if (loggedInUser.getRole() != Role.TRAINER) {
            throw new InsufficientRoleException(loggedInUser.getRole());
        }

        Trainer trainer = trainerService.findByBaseUser(loggedInUser);
        sessionOption.setTrainer(trainer);

        return sessionOptionRepository.save(sessionOption);
    }

    public void delete(long id) {
        sessionOptionRepository.deleteById(id);
    }
}
