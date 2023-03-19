package com.schol.gymmanager;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.DTOs.SubscriptionPlanDto;
import com.schol.gymmanager.model.DTOs.TrainerDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class TestUtils {

    public CustomerDto getTestCustomerDto() {
        return CustomerDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .userName("Janenator")
                .email("jane@gmail.com")
                .password("pwd")
                .build();
    }

    public Address getTestAddress() {
        return Address.builder()
                .zipcode("1227")
                .street("Bokor st.")
                .city("Budapest")
                .suite("suite")
                .build();
    }

    public Gym getTestGym() {
        return Gym.builder()
                .id(1)
                .address(getTestAddress())
                .name("Big Bulker")
                .status("Status")
                .build();
    }

    public Customer getTestCustomer() {
        return Customer.builder()
                .firstName("Jane")
                .lastName("Doe")
                .userName("Janenator")
                .email("jane@gmail.com")
                .passwordHash("pwd")
                .build();
    }

    public Trainer getTestTrainer() {
        return Trainer.builder()
                .gym(getTestGym())
                .userName("John")
                .build();
    }

    public Subscription getTestSubscription() {
        return Subscription.builder()
                .customer(getTestCustomer())
                .gym(getTestGym())
                .price(BigDecimal.valueOf(100))
                .cancelAtPeriodEnd(true)
                .ongoing(true)
                .defaultPaymentMethod(1)
                .build();
    }

    public SubscriptionPlan getTestSubscriptionPlan() {
        return SubscriptionPlan.builder()
                .gym(getTestGym())
                .customer(getTestCustomer())
                .price(BigDecimal.valueOf(100))
                .name("Sub name")
                .description("Sub desc")
                .durationInDays(30L)
                .startDate(LocalDateTime.parse("2023-01-13T15:12:12.000000"))
                .build();
    }

    public TrainerDto getTestTrainerDto() {
        return TrainerDto.builder()
                .gymId(getTestGym().getId())
                .email("trainer@gmail.com")
                .subscriptionPlans(Arrays.asList(getTestSubscriptionPlan()))
                .userName("John")
                .password("pwd")
                .build();
    }
}
