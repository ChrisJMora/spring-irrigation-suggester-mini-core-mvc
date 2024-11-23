package com.example.demo.model.agriculture;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SprinklerStatus {
    ON("Encendido"),
    OFF("Apagado");

    private final String description;

    public static SprinklerStatus fromDescription(String description) {
        for(SprinklerStatus status : SprinklerStatus.values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}
