package com.schol.gymmanager.model.DTOs;

import com.schol.gymmanager.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDto {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
