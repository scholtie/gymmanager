package com.schol.gymmanager.model;

import com.schol.gymmanager.model.user.Trainer;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="SessionOption")
public class SessionOption extends RepresentationModel<SessionOption> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    private String name;
    private BigDecimal price;
    private Long lengthMinutes;
    private String description;
}
