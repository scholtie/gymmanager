package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.SubscriptionPlan;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class TrainerDto implements Serializable {
    private final Long gymId;
//    @NotNull(message = "subscriptionPlan cannot be empty")
//    private final List<SubscriptionPlan> subscriptionPlans;
    @NotNull(message = "email cannot be empty")
    @NotEmpty
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private final String email;
    @NotNull(message = "password cannot be empty")
    private final String password;
    @NotEmpty(message = "userName cannot be empty")
    private final String userName;
    private final String firstName;
    private final String lastName;
    @NotEmpty(message = "gender cannot be empty")
    private final String gender;
    @NotEmpty(message = "status cannot be empty")
    private final String status;
}
