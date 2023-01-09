package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.SessionOption;
import com.schol.gymmanager.model.Trainer;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
public class SessionDto {
    private Long customerId;
    private Long trainerId;
    private Long optionId;
    private LocalDateTime start;
}
