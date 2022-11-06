package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="BusinessHours")
public class BusinessHours {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int day;

    private LocalTime openTime;

    private LocalTime closeTime;

    private LocalDate modifyDate;
}
