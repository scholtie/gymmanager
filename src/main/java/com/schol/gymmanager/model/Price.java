package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Price")
public class Price {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    private int stripePriceId;

    private int stripeProductId;

    private Long amount;

    private String description;
}
