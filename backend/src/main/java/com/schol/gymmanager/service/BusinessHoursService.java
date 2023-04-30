package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.DTOs.BusinessHoursDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.BusinessHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHoursService {
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private GymService gymService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private AuthService authService;

    public List<BusinessHours> setBusinessHours(List<BusinessHoursDto> businessHoursDtoList){
        List<BusinessHours> businessHoursList = new ArrayList<>();
        for (BusinessHoursDto businessHoursDTO : businessHoursDtoList) {
            Gym gym = new Gym();
            Trainer trainer = new Trainer();
            if (authService.getLoggedInUser().isPresent()) {
                Role role = authService.getLoggedInUser().get().getRole();
                if (role == Role.GYM)  {
                    gym = gymService.findByBaseUser(authService.getLoggedInUser().get());
                    trainer = null;
                } else if (role == Role.TRAINER) {
                    trainer = trainerService.findByBaseUser(authService.getLoggedInUser().get());
                    gym = null;
                }
                else{throw new InsufficientRoleException(role);
                }
            }
            BusinessHours businessHour = BusinessHours.builder()
                    .available(businessHoursDTO.isAvailable())
                    .gym(gym)
                    .trainer(trainer)
                    .day(businessHoursDTO.getDay())
                    .closeTime(businessHoursDTO.getCloseTime())
                    .openTime(businessHoursDTO.getOpenTime()).build();
            businessHoursList.add(businessHour);
            businessHoursRepository.save(businessHour);
        }
        return businessHoursList;
    }

    public List<BusinessHours> findBusinessHoursForGym(long id){
        return businessHoursRepository.findAllByGymId(id);
    }

    public List<BusinessHours> findBusinessHoursForTrainer(long id){
        return businessHoursRepository.findAllByTrainerId(id);
    }

    public BusinessHours findBusinessHourForTrainerByDay(long trainerId, DayOfWeek day){
        return businessHoursRepository.findAllByTrainerIdAndDay(trainerId, day);
    }

}
