package com.schol.gymmanager.model.enums;

public enum PaymentMethod {
    CASH("cash"),
    CARD("card"),
    TRANSFER("transfer");

    private final String value;

    PaymentMethod(String paymentMethod) {
        this.value = paymentMethod;
    }

    public String getValue() {
        return this.value;
    }
}
