package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GymService {
    @Autowired
    private GymRepository gymRepository;

    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    public Gym findById(long id) {
        return gymRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Gym", id));
    }
}
