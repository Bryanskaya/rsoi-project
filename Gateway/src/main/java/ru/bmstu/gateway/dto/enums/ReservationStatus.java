package ru.bmstu.gateway.dto.enums;

public enum ReservationStatus {
    PAID("PAID"),
    REVERSED("REVERSED"),
    CANCELED("CANCELED");

    private String name;

    ReservationStatus(String name) {
        this.name = name;
    }
}
