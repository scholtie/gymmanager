package com.schol.gymmanager.model;

import com.schol.gymmanager.model.enums.PaymentMethod;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Subscription")
public class Subscription extends RepresentationModel<Subscription> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "subscriptionPlan_id")
    private SubscriptionPlan subscriptionPlan;
    //ongoing should always be calculated from period start and end
    private boolean ongoing;
    private LocalDate currentPeriodStart;
    private LocalDate currentPeriodEnd;
    private boolean cancelAtPeriodEnd;
    private LocalDateTime canceledAt;
    private PaymentMethod defaultPaymentMethod;
}
