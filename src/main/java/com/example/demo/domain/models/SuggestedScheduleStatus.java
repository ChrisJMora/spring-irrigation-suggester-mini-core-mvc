package com.example.demo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuggestedScheduleStatus {
    PENDING("Pendiente"),
    ACCEPTED("Aceptado"),
    CANCELED("Cancelado");

    private final String description;

    public static SuggestedScheduleStatus fromDescription(String description) {
        for (SuggestedScheduleStatus status : SuggestedScheduleStatus.values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}
