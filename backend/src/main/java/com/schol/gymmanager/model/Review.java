package com.schol.gymmanager.model;

import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.user.Gym;
import com.schol.gymmanager.model.user.Trainer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="TrainerReview")
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gym_id")
    private Gym gym;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private double rating;
    private String comment;
}
