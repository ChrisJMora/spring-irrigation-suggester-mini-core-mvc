package com.example.demo.exception;

import jakarta.persistence.Table;

public class SaveRecordFailException extends RuntimeException {
    public SaveRecordFailException(Class<?> entityClass) {
        super("The record from table: " + entityClass.getAnnotation(Table.class).name() + " fail being saved.");
    }
}
