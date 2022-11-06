package com.schol.gymmanager;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String email) {
        super("This email is already registered: " + email);
    }
}
