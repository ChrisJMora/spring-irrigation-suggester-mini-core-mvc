package com.example.demo.exception;

import jakarta.persistence.Table;

public class EmptyFilterException extends RuntimeException {
    public EmptyFilterException(Class<?> entityClass, Class<?> filterClass) {
        super("No data available in the table: " + entityClass.getAnnotation(Table.class).name()
                + "using filter: " + filterClass.getAnnotation(Table.class));
    }
}
