package com.schol.gymmanager.controller;

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

    @GetMapping("/")
    public List<SessionOption> findAll(){
        List<SessionOption> sessionOptions = sessionOptionService.findAll();
        for (SessionOption sessionOption : sessionOptions) {
            addLinks(sessionOption);        }
        return sessionOptions;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        sessionOptionService.delete(id);
    }

    private void addLinks(SessionOption sessionOption) {
        sessionOption.add(linkTo(methodOn(SessionOptionController.class).findById(sessionOption.getId())).withSelfRel());
    }
}