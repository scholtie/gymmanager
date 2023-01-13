package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Gym;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
public class SubscriptionPlanDto {
    private Long gymId;
    private String name;
    private String description;
    private Long durationInDays;
    private BigDecimal price;
}
