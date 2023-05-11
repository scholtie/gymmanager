package com.schol.gymmanager.model.auth;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthRequest {
    private String email;
    private String password;
}
