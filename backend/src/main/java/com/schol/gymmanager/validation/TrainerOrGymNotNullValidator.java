package com.schol.gymmanager.validation;

import com.schol.gymmanager.model.BusinessHours;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrainerOrGymNotNullValidator implements ConstraintValidator<TrainerOrGymNotNull, BusinessHours> {

    @Override
    public boolean isValid(BusinessHours businessHours, ConstraintValidatorContext context) {
        if (businessHours.getTrainer() == null && businessHours.getGym() == null) {
            return false;
        }
        return businessHours.getTrainer() == null || businessHours.getGym() == null;
    }

}
