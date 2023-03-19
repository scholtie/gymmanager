package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.repository.*;
import com.schol.gymmanager.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/{id}")
    public Session findById(@PathVariable long id){
        return sessionService.findById(id);
    }

    @GetMapping("/")
    public List<Session> findAll(){
        return sessionService.findAll();
    }

    @PostMapping("/")
    public Session create(@RequestBody SessionDto sessionDto){
        return sessionService.create(sessionDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        //refund money?
        sessionService.delete(id);
    }
}