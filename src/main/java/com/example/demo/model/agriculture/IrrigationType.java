package com.example.demo.model.agriculture;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IrrigationType {
    DRIP("Goteo"),
    ASPERSION("Aspersion"),
    GROOVE("Surco");

    private final String description;

    public static IrrigationType fromDescription(String description) {
        for(IrrigationType type : IrrigationType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}
