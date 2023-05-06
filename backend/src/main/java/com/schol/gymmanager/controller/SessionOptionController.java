package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.DTOs.SessionOptionDto;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.service.SessionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping(value = "/sessionoptions")
@RestController
public class SessionOptionController {
    @Autowired
    private SessionOptionService sessionOptionService;

    @GetMapping("/{id}")
    public SessionOption findById(@PathVariable long id){
        SessionOption sessionOption = sessionOptionService.findById(id);
        addLinks(sessionOption);
        return sessionOption;
    }

    @GetMapping("/findByTrainer/{id}")
    public List<SessionOption> findAllByTrainer(@PathVariable long id){
        return sessionOptionService.findAllByTrainer(id);
    }

    @GetMapping("/findByLoggedInTrainer")
    public List<SessionOption> findAllByTrainer(){
        return sessionOptionService.findAllByLoggedInTrainer();
    }

    @GetMapping("/")
    public List<SessionOption> findAll(){
        List<SessionOption> sessionOptions = sessionOptionService.findAll();
        for (SessionOption sessionOption : sessionOptions) {
            addLinks(sessionOption);        }
        return sessionOptions;
    }

    @PostMapping("/")
    public SessionOption create(@RequestBody SessionOptionDto sessionOptionDto){
        SessionOption sessionOptionToSave = sessionOptionService.create(sessionOptionDto);
        addLinks(sessionOptionToSave);
        return sessionOptionToSave;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        sessionOptionService.delete(id);
    }

    private void addLinks(SessionOption sessionOption) {
        sessionOption.add(linkTo(methodOn(SessionOptionController.class).findById(sessionOption.getId())).withSelfRel());
    }
}