package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.Gym;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionPlanDto {
    private Long gymId;
    private String name;
    private String description;
    private Long durationInDays;
    private BigDecimal price;
    private Customer customer;
    private LocalDateTime startDate;
}
