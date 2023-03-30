package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.DTOs.SessionOptionDto;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.SessionOptionRepository;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class SessionOptionService {
    @Autowired
    private SessionOptionRepository sessionOptionRepository;

    @Autowired
    private TrainerService trainerService;

    public SessionOption findById(long id){
        return sessionOptionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("SessionOption", id));
    }

    public List<SessionOption> findAll(){
        return sessionOptionRepository.findAll();
    }

    public SessionOption create(SessionOptionDto sessionOptionDto){
        SessionOption sessionOptionToSave = new SessionOption();
        sessionOptionToSave.setName(sessionOptionDto.getName());
        sessionOptionToSave.setPrice(sessionOptionDto.getPrice());
        sessionOptionToSave.setMaxPeople(sessionOptionDto.getMaxPeople());
        sessionOptionToSave.setLengthMinutes(sessionOptionDto.getLengthMinutes());
        sessionOptionToSave.setTrainer(trainerService.findById(sessionOptionDto.getTrainerId()));
        return sessionOptionRepository.save(sessionOptionToSave);
    }

    public void delete(long id) {
        sessionOptionRepository.deleteById(id);
    }
}
