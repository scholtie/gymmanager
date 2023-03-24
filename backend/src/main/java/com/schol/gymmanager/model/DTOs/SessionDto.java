package com.schol.gymmanager.model.DTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SessionDto {
    private Long customerId;
    private Long trainerId;
    private Long optionId;
    private LocalDateTime start;
}
