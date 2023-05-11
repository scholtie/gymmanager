package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.Address;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.DTOs.GymDto;
import com.schol.gymmanager.model.Geo;
import com.schol.gymmanager.model.user.Gym;
import com.schol.gymmanager.repository.AddressRepository;
import com.schol.gymmanager.repository.GeoRepository;
import com.schol.gymmanager.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {
    private final GymRepository gymRepository;
    private final AddressRepository addressRepository;
    private final GeoRepository geoRepository;
    private final AuthService authService;

    @Autowired
    public GymService(GymRepository gymRepository, AddressRepository addressRepository,
                      GeoRepository geoRepository, AuthService authService) {
        this.gymRepository = gymRepository;
        this.addressRepository = addressRepository;
        this.geoRepository = geoRepository;
        this.authService = authService;
    }

    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    public Gym findById(long id) {
        return gymRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gym", id));
    }

    public Gym findByBaseUser(BaseUser baseUser) {
        return gymRepository.findByBaseUser(baseUser);
    }

    public Gym create(GymDto gymDto) {
        Gym gym = new Gym();
        Address address = new Address();
        Geo geo = new Geo();

        if (authService.getLoggedInUser().isPresent()) {
            gym.setBaseUser(authService.getLoggedInUser().get());
        }

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
