package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Gym")
public class Gym {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany
    @JoinColumn(name = "businesshours_id")
    private List<BusinessHours> businessHours;

    @OneToMany
    @JoinColumn(name = "subscription_id")
    private List<Subscription> subscriptions;

    @OneToMany
    @JoinColumn(name = "trainer_id")
    private List<Trainer> trainers;
}