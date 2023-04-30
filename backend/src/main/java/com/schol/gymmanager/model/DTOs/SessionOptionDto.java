package com.schol.gymmanager.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionOptionDto {
    private String name;
    private BigDecimal price;
    private Integer maxPeople;
    private Long lengthMinutes;
}