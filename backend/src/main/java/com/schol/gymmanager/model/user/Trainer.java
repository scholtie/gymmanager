package com.schol.gymmanager.model.user;

import com.schol.gymmanager.model.enums.Gender;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Trainer")
public class Trainer extends RepresentationModel<Trainer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @OneToOne
    @JoinColumn(name = "base_user_id")
    private BaseUser baseUser;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gym_id")
    private Gym gym;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String status;
    private String imgPath;
    private String introduction;
    private long rating;
}
