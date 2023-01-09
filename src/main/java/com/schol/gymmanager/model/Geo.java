package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Geo")
public class Geo {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private double lat;
    private double lng;
}
