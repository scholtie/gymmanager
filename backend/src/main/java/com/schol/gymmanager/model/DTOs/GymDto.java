package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GymDto {
    private String name;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private double lat;
    private double lng;
    private String status;
    private String avatarImgPath;
    private String about;
}
