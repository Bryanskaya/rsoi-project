package ru.bmstu.gateway.dto.enums;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private String name;

    Role(String name) {
        this.name = name;
    }
}
