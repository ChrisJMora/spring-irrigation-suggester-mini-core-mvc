package com.example.demo.domain.exceptions;

import jakarta.persistence.Table;

public class EmptyFilterException extends RuntimeException {
    public EmptyFilterException(Class<?> entityClass, Class<?>... filterClasses) {
        super(buildMessage(entityClass, filterClasses));
    }

    private static String buildMessage(Class<?> entityClass, Class<?>... filterClasses) {
        StringBuilder message = new StringBuilder("No data available in the table: ");
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            message.append(tableAnnotation.name());
        } else {
            message.append(entityClass.getSimpleName());
        }
        message.append(" using filters: ");
        for (Class<?> filterClass : filterClasses) {
            Table filterTableAnnotation = filterClass.getAnnotation(Table.class);
            if (filterTableAnnotation != null) {
                message.append(filterTableAnnotation.name()).append(", ");
            } else {
                message.append(filterClass.getSimpleName()).append(", ");
            }
        }
        if (filterClasses.length > 0) {
            message.setLength(message.length() - 2);
        }
        return message.toString();
    }
}

