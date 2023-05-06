package com.schol.gymmanager.model.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schol.gymmanager.model.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;
    private Long subscriptionPlanId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private PaymentMethod paymentMethod;
    private boolean cancelAtPeriodEnd;
}
