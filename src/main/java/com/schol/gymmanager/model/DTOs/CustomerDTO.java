package com.schol.gymmanager.model.DTOs;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CustomerDTO {

    @NotNull(message = "userName cannot be empty")
    private String userName;

    @NotNull(message = "email cannot be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull(message = "password cannot be empty")
    private String password;

    @NotNull(message = "firstName cannot be empty")
    private String firstName;

    @NotNull(message = "secondName cannot be empty")
    private String lastName;
}
