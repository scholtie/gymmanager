package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Subscription")
public class Subscription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;
    private boolean ongoing;
    private LocalDateTime currentPeriodStart;
    private LocalDateTime currentPeriodEnd;
    private boolean cancelAtPeriodEnd;
    private LocalDateTime canceledAt;
    private int defaultPaymentMethod;
    private BigDecimal price;
}
