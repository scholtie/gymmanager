package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="SessionOption")
public class SessionOption {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer maxPeople;
    private Long lengthMinutes;
    @OneToMany(mappedBy = "option")
    private List<Session> sessions;
}
