package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.SessionOptionRepository;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        session.setStart(sessionDto.getStart());
        session.setEnd(session.getStart().plusMinutes(session.getOption().getLengthMinutes()));
        return sessionRepository.save(session);
    }
}