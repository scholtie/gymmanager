package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.BusinessHours;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    List<BusinessHours> findAllByGymId(long gymId);
    List<BusinessHours> findAllByTrainerId(long trainerId);
    BusinessHours findAllByTrainerIdAndDay(long trainerId, DayOfWeek day);
}
