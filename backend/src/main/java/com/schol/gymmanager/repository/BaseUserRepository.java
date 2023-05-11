package com.schol.gymmanager.repository;

import com.schol.gymmanager.model.user.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
    boolean existsUserAccountByEmail (String email);
    Optional<BaseUser> findByEmail(String email);
}
