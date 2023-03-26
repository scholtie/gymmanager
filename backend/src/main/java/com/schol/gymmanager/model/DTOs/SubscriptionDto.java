package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long customerId;
    private Long subscriptionPlanId;
    private LocalDateTime startDate;
    private PaymentMethod paymentMethod;
}
