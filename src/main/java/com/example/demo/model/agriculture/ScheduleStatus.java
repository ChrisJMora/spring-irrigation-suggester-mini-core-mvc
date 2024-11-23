package com.example.demo.model.agriculture;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScheduleStatus {
    PENDING("Pendiente"),
    IN_PROGRESS("En Progreso"),
    COMPLETE("Completado"),
    CANCELED("Cancelado");

    private final String description;

    public static ScheduleStatus fromDescription(String description) {
        for (ScheduleStatus status : ScheduleStatus.values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}
