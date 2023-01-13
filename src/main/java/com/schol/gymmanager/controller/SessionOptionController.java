package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.SessionOptionRepository;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/sessionoptions")
@RestController
public class SessionOptionController {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionOptionRepository sessionOptionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping("/{id}")
    public SessionOption findById(@PathVariable long id){
        return sessionOptionRepository.findById(id).get();
    }

    @GetMapping("/")
    public List<SessionOption> findAll(){
        return sessionOptionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        sessionOptionRepository.deleteById(id);
    }
}