package com.example.demo.domain.models;

import java.time.LocalDate;

public record DateRange(LocalDate startDate, LocalDate endDate) {
    public DateRange {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }
    }

    public boolean isDateWithinRange(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}
