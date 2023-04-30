package com.schol.gymmanager.model.DTOs;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessHoursDto {
    private DayOfWeek day;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDate modifyDate;
    private boolean available;
}
