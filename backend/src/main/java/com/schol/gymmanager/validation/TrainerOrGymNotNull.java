package com.schol.gymmanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrainerOrGymNotNullValidator.class)
public @interface TrainerOrGymNotNull {

    String message() default "Either trainer or gym must be non-null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}