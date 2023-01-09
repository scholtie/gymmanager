package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;

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
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gym_id")
    private Gym gym;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="trainer_id")
    private Trainer trainer;
    private String name;
    private String description;
    private String duration;
    private double price;
}
