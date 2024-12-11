package com.example.demo.exception;

public class FutureScheduleException extends RuntimeException {
    public FutureScheduleException() {
        super("The schedule's start time must be in the future.");
    }
}