package com.schol.gymmanager.exception;

import com.schol.gymmanager.model.enums.Role;

public class SubscriptionException extends RuntimeException{
    public SubscriptionException() {
        super("There is already an ongoing subscription for this user");
    }
}

