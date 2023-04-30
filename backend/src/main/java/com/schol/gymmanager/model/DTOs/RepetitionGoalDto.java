package com.schol.gymmanager.model.DTOs;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepetitionGoalDto {
    private String name;
    private LocalDate date;
    private Integer repetitions;
    private Integer value;
}
