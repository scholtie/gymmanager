package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Gym;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessHoursDto {
    private long gymId;
    private int day;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDate modifyDate;
}
