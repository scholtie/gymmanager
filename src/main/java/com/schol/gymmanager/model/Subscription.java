package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Subscription")
public class Subscription {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    private String stripeSubscriptionId;

    private int status;

    private LocalDateTime currentPeriodStart;

    private LocalDateTime currentPeriodEnd;

    private boolean cancelAtPeriodEnd;

    private LocalDateTime cancelAt;

    private LocalDateTime canceledAt;

    private LocalDateTime endedAt;

    private int defaultPaymentMethod;
}
