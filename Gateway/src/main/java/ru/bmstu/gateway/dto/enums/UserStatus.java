package ru.bmstu.gateway.dto.enums;

public enum UserStatus {
    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLD("GOLD");

    private String name;

    UserStatus(String name) {
        this.name = name;
    }
}