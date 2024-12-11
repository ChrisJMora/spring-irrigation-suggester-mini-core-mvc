package com.example.demo.exception;

public class ScheduleConflictException extends RuntimeException {
    public ScheduleConflictException() {
        super("The schedule conflicts with an existing schedule.");
    }
}
