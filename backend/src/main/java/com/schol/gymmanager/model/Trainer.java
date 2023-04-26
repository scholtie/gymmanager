package com.schol.gymmanager.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.hateoas.RepresentationModel;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Timestamp;

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
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gym_id")
    private Gym gym;
    @Column(unique=true, nullable = false)
    @NotEmpty
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotNull
    private String passwordHash;
    private String timeZone;
    private Timestamp createTime;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String status;
    private String imgPath;
    private String introduction;
    private long rating;
}
