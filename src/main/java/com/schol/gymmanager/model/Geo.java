package com.schol.gymmanager.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Long id;

    private double lat;

    private double lng;
}
