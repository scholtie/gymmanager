package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="SubscriptionPlan")
public class SubscriptionPlan {
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
