package com.example.demo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IrrigationType {
    DRIP("Goteo", 1.5f),
    ASPERSION("Aspersion", 2.0f),
    GROOVE("Surco", 3.0f);

    private final String description;
    private final float flowRate;

    public static IrrigationType fromDescription(String description) {
        for(IrrigationType type : IrrigationType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}
