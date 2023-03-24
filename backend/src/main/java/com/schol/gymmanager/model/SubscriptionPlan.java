package com.schol.gymmanager.model;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;
    private String name;
    private String description;
    private Long durationInDays;
    private BigDecimal price;
    private LocalDateTime startDate;
}
