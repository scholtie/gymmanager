package com.schol.gymmanager.model.DTOs;

import lombok.*;

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

