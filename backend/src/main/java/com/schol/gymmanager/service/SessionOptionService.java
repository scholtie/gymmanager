package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
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

    public SessionOption findById(long id){
        return sessionOptionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("SessionOption", id));
    }

    public List<SessionOption> findAll(){
        return sessionOptionRepository.findAll();
    }

    public SessionOption create(SessionOption sessionOption){
        SessionOption sessionOptionToSave = new SessionOption();
        sessionOptionToSave.setName(sessionOption.getName());
        sessionOptionToSave.setPrice(sessionOption.getPrice());
        sessionOptionToSave.setMaxPeople(sessionOption.getMaxPeople());
        sessionOptionToSave.setLengthMinutes(sessionOption.getLengthMinutes());
        return sessionOptionRepository.save(sessionOptionToSave);
    }

    public void delete(long id) {
        sessionOptionRepository.deleteById(id);
    }
}
