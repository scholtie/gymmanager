package com.schol.gymmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Customer")
public class Customer extends RepresentationModel<Customer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "email cannot be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotNull(message = "password cannot be empty")
    @NotEmpty
    private String passwordHash;
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "userName cannot be empty")
    private String userName;
    private String timeZone;
    private Timestamp createTime;
    @NotNull(message = "firstName cannot be empty")
    private String firstName;
    @NotNull(message = "secondName cannot be empty")
    private String lastName;

}
