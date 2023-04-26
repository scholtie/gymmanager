package com.schol.gymmanager.model.DTOs;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    private Long trainerId;
    private Long optionId;
    private LocalDateTime start;
}
