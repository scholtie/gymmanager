package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    @OneToOne
    @JoinColumn(name = "geo_id")
    private Geo geo;
}
