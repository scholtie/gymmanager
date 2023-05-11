package com.schol.gymmanager.model.user;

import com.schol.gymmanager.model.Address;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Gym")
public class Gym extends RepresentationModel<Gym> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @OneToOne
    @JoinColumn(name = "base_user_id")
    private BaseUser baseUser;
    private String name;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String status;
    private String avatarImgPath;
    private String about;
}