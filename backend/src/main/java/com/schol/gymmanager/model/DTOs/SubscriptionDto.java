package com.schol.gymmanager.model.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schol.gymmanager.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long subscriptionPlanId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private PaymentMethod paymentMethod;
}
