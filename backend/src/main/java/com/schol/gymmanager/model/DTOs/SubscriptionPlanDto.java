package com.schol.gymmanager.model.DTOs;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionPlanDto {
    private String name;
    private String description;
    private Long durationInDays;
    private BigDecimal price;
}
