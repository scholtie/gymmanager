package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.SessionOptionRepository;
import com.schol.gymmanager.repository.SessionRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import com.schol.gymmanager.service.SessionOptionService;
import com.schol.gymmanager.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/sessionoptions")
@RestController
public class SessionOptionController {
    @Autowired
    private SessionOptionService sessionOptionService;

    @GetMapping("/{id}")
    public SessionOption findById(@PathVariable long id){
        return sessionOptionService.findById(id);}

    @GetMapping("/")
    public List<SessionOption> findAll(){
        return sessionOptionService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        sessionOptionService.delete(id);
    }
}