package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionOptionService sessionOptionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TrainerService trainerService;

    public Session findById(long id){
        return sessionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Session", id));
    }

    public List<Session> findAll(){
        return sessionRepository.findAll();
    }

    public Session create(SessionDto sessionDto){
        Session session = new Session();
        session.setStart(sessionDto.getStart());
        Customer customer = customerService.findById(sessionDto.getCustomerId());
        Trainer trainer = trainerService.findById(sessionDto.getTrainerId());
        SessionOption option = sessionOptionService.findById(sessionDto.getOptionId());
        session.setCustomer(customer);
        session.setTrainer(trainer);
        session.setOption(option);
        session.setEnd(sessionDto.getStart().plusMinutes(session.getOption().getLengthMinutes()));
        return sessionRepository.save(session);
    }

    public void delete(long id) {
        //refund money?
        sessionRepository.deleteById(id);
    }
}
