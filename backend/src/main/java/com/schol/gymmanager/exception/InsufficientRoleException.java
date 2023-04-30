package com.schol.gymmanager.exception;

import com.schol.gymmanager.model.enums.Role;

public class InsufficientRoleException  extends RuntimeException{
    public InsufficientRoleException(Role role) {
        super("User with role " + role + " is unable to perform this operation.");
    }

    public InsufficientRoleException(){
        super("Insufficient permissions");
    }
}
