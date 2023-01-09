package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RequestMapping(value = "/sessions")
@RestController
public class SessionController {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionOptionRepository sessionOptionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @PostMapping("/")
    public Session create(@RequestBody SessionDto sessionDto){
        Session session = new Session();
        session.setStart(sessionDto.getStart());
        Optional<Customer> customer = customerRepository.findById(sessionDto.getCustomerId());
        Optional<Trainer> trainer = trainerRepository.findById(sessionDto.getTrainerId());
        Optional<SessionOption> option = sessionOptionRepository.findById(sessionDto.getOptionId());
        customer.ifPresent(session::setCustomer);
        trainer.ifPresent(session::setTrainer);
        option.ifPresent(session::setOption);
        session.setEnd(sessionDto.getStart().plusMinutes(session.getOption().getLengthMinutes()));
        return sessionRepository.save(session);
    }
}