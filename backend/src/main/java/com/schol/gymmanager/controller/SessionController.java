package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/{id}")
    public Session findById(@PathVariable long id) {
        Session session = sessionService.findById(id);
        addLinks(session);
        return session;
    }

    @GetMapping("/")
    public List<Session> findAll() {
        List<Session> sessions = sessionService.findAllForCustomer();
        for (Session session : sessions) {
            addLinks(session);
        }
        return sessions;
    }

    @PostMapping("/")
    public Session create(@RequestBody SessionDto sessionDto) {
        Session session = sessionService.create(sessionDto);
        addLinks(session);
        return session;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        //refund money?
        sessionService.delete(id);
    }

    private void addLinks(Session session) {
        if (session != null) {
            session.add(linkTo(methodOn(SessionController.class).findById(session.getId())).withSelfRel());

            if (session.getCustomer() != null) {
                Link customerLink = linkTo(methodOn(CustomerController.class).findById(session.getCustomer().getId())).withRel("customer");
                session.add(customerLink);
            }

            if (session.getTrainer() != null) {
                Link trainerLink = linkTo(methodOn(TrainerController.class).findById(session.getTrainer().getId())).withRel("trainer");
                session.add(trainerLink);
            }

            if (session.getOption() != null) {
                Link sessionOptionLink = linkTo(methodOn(SessionOptionController.class).findById(session.getOption().getId())).withRel("sessionOption");
                session.add(sessionOptionLink);
            }
        }
    }
}