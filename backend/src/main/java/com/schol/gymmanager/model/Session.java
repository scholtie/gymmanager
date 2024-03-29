package com.schol.gymmanager.model;

import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.user.Trainer;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Session")
public class Session extends RepresentationModel<Session> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    @ManyToOne
    @JoinColumn(name = "session_option_id")
    private SessionOption option;
    private LocalDateTime start;
    private LocalDateTime end;
}
