package com.schol.gymmanager.model.user;

import com.schol.gymmanager.model.enums.Gender;
import com.schol.gymmanager.model.progress.NumericGoal;
import com.schol.gymmanager.model.progress.RepetitionGoal;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Customer")
public class Customer{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @OneToOne
    @JoinColumn(name = "base_user_id")
    private BaseUser baseUser;
    private String firstName;
    private String lastName;
    private Gender gender;
    @OneToMany
    private List<NumericGoal> numericGoals;
    @OneToMany
    private List<RepetitionGoal> repetitionGoals;
}
