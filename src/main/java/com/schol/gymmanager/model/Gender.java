package com.schol.gymmanager.model;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String value;

    Gender(String gender) {
        this.value = gender;
    }

    public String getValue() {
        return this.value;
    }
}
