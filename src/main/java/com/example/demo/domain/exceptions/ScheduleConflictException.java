package com.example.demo.domain.exceptions;

public class ScheduleConflictException extends RuntimeException {
    public ScheduleConflictException() {
        super("The schedule conflicts with an existing schedule.");
    }
}
