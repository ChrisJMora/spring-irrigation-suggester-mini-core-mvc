package com.example.demo.exception;

import jakarta.persistence.Table;

public class EmptyTableException extends RuntimeException {
    public EmptyTableException(Class<?> entityClass) {
        super("No data available in the table: " + entityClass.getAnnotation(Table.class).name());
    }
}
