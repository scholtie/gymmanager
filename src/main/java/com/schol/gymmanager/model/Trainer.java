package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Trainer")
public class Trainer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gym_id")
    private Gym gym;
    @OneToMany(mappedBy = "trainer")
    private List<SubscriptionPlan> subscriptionPlans;
    @OneToMany(mappedBy = "trainer")
    private List<Customer> customers;
    @OneToMany(mappedBy = "trainer")
    private List<Session> sessions;
    @Column(unique=true, nullable = false)
    @NotEmpty
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotNull
    private String passwordHash;
    @Column(unique=true, nullable = false)
    @NotEmpty
    private String userName;
    private String timeZone;
    private Timestamp createTime;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
}
