package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.BusinessHoursDto;
import com.schol.gymmanager.model.DTOs.GymDto;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.repository.BusinessHoursRepository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.mediastore.MediaStoreClient;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.mediastore.model.DescribeContainerRequest;
import software.amazon.awssdk.services.mediastore.model.DescribeContainerResponse;
import com.schol.gymmanager.repository.AddressRepository;
import com.schol.gymmanager.repository.GeoRepository;
import com.schol.gymmanager.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class GymService {
    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GeoRepository geoRepository;

    @Autowired
    private BusinessHoursRepository businessHoursRepository;

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

    public BusinessHours setBusinessHours(BusinessHoursDto businessHoursDTO){
        BusinessHours businessHours = new BusinessHours();
        businessHours.setGym(findById(businessHoursDTO.getGymId()));
        businessHours.setDay(businessHoursDTO.getDay());
        businessHours.setCloseTime(businessHoursDTO.getCloseTime());
        businessHours.setOpenTime(businessHoursDTO.getOpenTime());
        businessHours.setModifyDate(LocalDate.now());
        return businessHoursRepository.save(businessHours);
    }
}
