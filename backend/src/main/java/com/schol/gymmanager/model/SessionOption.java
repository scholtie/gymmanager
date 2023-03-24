package com.schol.gymmanager.model;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="SessionOption")
public class SessionOption {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer maxPeople;
    private Long lengthMinutes;
//    @OneToMany(mappedBy = "option")
//    private List<Session> sessions;
}
