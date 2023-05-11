package com.schol.gymmanager.model.DTOs;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto implements Serializable {
    private Long gymId;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private String imgPath;
    private String introduction;
}
