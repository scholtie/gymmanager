package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Address;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.GymDto;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.Geo;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Session;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.repository.AddressRepository;
import com.schol.gymmanager.repository.GeoRepository;
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

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GeoRepository geoRepository;

    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    public Gym findById(long id) {
        return gymRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Gym", id));
    }

    public Gym create(GymDto gymDto){
        Gym gym = new Gym();
        Address address = new Address();
        Geo geo = new Geo();
        gym.setAbout(gymDto.getAbout());
        gym.setName(gymDto.getName());
        gym.setStatus(gymDto.getStatus());
        gym.setAvatarImgPath(gymDto.getAvatarImgPath());
        address.setCity(gymDto.getCity());
        address.setSuite(gymDto.getSuite());
        address.setStreet(gymDto.getStreet());
        address.setZipcode(gymDto.getZipcode());
        geo.setLng(gymDto.getLng());
        geo.setLat(gymDto.getLat());
        address.setGeo(geo);
        gym.setAddress(address);
        geoRepository.save(geo);
        addressRepository.save(address);
        return gymRepository.save(gym);
    }
}
