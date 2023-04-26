package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.Trainer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long trainerId;
    private Long gymId;
    private int rating;
    private String comment;
}

