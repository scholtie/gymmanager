package com.schol.gymmanager.model;

import com.schol.gymmanager.model.user.Gym;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="SubscriptionPlan")
public class SubscriptionPlan extends RepresentationModel<SubscriptionPlan> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gym_id")
    private Gym gym;
    private String name;
    private String description;
    private Long durationInDays;
    private BigDecimal price;
}
