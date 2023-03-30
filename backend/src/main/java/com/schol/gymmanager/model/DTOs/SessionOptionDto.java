package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionOptionDto {
    private Long trainerId;
    private String name;
    private BigDecimal price;
    private Integer maxPeople;
    private Long lengthMinutes;
}