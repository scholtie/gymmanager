package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.BaseUser;
import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.DTOs.BusinessHoursDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Trainer;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.repository.BusinessHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessHoursService {
    private final BusinessHoursRepository businessHoursRepository;
    private final GymService gymService;
    private final TrainerService trainerService;
    private final AuthService authService;

    @Autowired
    public BusinessHoursService(BusinessHoursRepository businessHoursRepository,
                                GymService gymService,
                                TrainerService trainerService,
                                AuthService authService) {
        this.businessHoursRepository = businessHoursRepository;
        this.gymService = gymService;
        this.trainerService = trainerService;
        this.authService = authService;
    }

    public List<BusinessHours> setBusinessHours(List<BusinessHoursDto> businessHoursDtoList) {
        List<BusinessHours> businessHoursList = new ArrayList<>();
        for (BusinessHoursDto businessHoursDTO : businessHoursDtoList) {
            Optional<Role> loggedInUserRole = authService.getLoggedInUser().map(BaseUser::getRole);
            Gym gym = null;
            Trainer trainer = null;
            if (loggedInUserRole.isPresent()) {
                Role role = loggedInUserRole.get();
                if (role == Role.GYM) {
                    gym = gymService.findByBaseUser(authService.getLoggedInUser().get());
                } else if (role == Role.TRAINER) {
                    trainer = trainerService.findByBaseUser(authService.getLoggedInUser().get());
                } else {
                    throw new InsufficientRoleException(role);
                }
            }
            BusinessHours businessHour = BusinessHours.builder()
                    .available(businessHoursDTO.isAvailable())
                    .gym(gym)
                    .trainer(trainer)
                    .day(businessHoursDTO.getDay())
                    .closeTime(businessHoursDTO.getCloseTime())
                    .openTime(businessHoursDTO.getOpenTime())
                    .build();
            businessHoursRepository.save(businessHour);
            businessHoursList.add(businessHour);
        }
        return businessHoursList;
    }

    public List<BusinessHours> findBusinessHoursForGym(long gymId) {
        return businessHoursRepository.findAllByGymId(gymId);
    }

    public List<BusinessHours> findBusinessHoursForTrainer(long trainerId) {
        return businessHoursRepository.findAllByTrainerId(trainerId);
    }

    public BusinessHours findBusinessHourForTrainerByDay(long trainerId, DayOfWeek day) {
        return businessHoursRepository.findAllByTrainerIdAndDay(trainerId, day);
    }

}
