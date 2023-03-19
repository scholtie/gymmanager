package com.schol.gymmanager.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName, Long id) {
        super("Could not find " + entityName + " with id: " + id);
    }
}
