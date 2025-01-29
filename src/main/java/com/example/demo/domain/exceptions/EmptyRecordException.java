package com.example.demo.domain.exceptions;

import jakarta.persistence.Table;

public class EmptyRecordException extends RuntimeException {
    public EmptyRecordException(Class<?> entityClass) {
        super("The record from table: " + entityClass.getAnnotation(Table.class).name() + " is empty.");
    }
}
