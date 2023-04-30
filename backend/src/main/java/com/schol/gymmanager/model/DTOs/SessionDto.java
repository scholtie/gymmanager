package com.schol.gymmanager.model.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {
    private Long trainerId;
    private Long optionId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;
}
