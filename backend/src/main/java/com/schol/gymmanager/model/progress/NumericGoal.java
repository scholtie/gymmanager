package com.schol.gymmanager.model.progress;

import com.schol.gymmanager.model.Customer;
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
    private LocalDate date;
    private Integer value;
    @ManyToOne
    private Customer customer;

}
