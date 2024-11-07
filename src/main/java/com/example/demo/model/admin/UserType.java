package com.example.demo.model.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    ADMINISTRATOR("Administrador"),
    SUPERVISOR("Supervisor");

    private final String description;

    public static UserType fromDescription(String description) {
        for (UserType type : UserType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}
