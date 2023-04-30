package com.schol.gymmanager.model;

import com.schol.gymmanager.validation.TrainerOrGymNotNull;
import lombok.*;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TrainerOrGymNotNull
@Table(name = "BusinessHours")
public class BusinessHours {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    private DayOfWeek day;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDate modifyDate;
    private boolean available;
}
