package com.schol.gymmanager.model.progress;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="NumericGoal")
public class NumericGoal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private LocalDate start;
    private LocalDate finish;
    private Integer startValue;
    private Integer goalValue;

}
