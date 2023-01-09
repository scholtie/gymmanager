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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "gym")
    private List<BusinessHours> businessHours;
    @OneToMany(mappedBy = "gym")
    private List<Trainer> trainers;
    @OneToMany(mappedBy = "gym")
    private List<SubscriptionPlan> subscriptionPlans;
    private String status;
}