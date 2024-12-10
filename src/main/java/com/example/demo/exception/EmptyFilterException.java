package com.example.demo.exception;

import jakarta.persistence.Table;

public class EmptyFilterException extends RuntimeException {
    public EmptyFilterException(Class<?> entityClass, Class<?>... filterClasses) {
        super(buildMessage(entityClass, filterClasses));
    }

    private static String buildMessage(Class<?> entityClass, Class<?>... filterClasses) {
        StringBuilder message = new StringBuilder("No data available in the table: ");

        // Check if the entity class has the @Table annotation
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            message.append(tableAnnotation.name());
        } else {
            message.append(entityClass.getSimpleName()); // Fallback to class name
        }

        message.append(" using filters: ");
        for (Class<?> filterClass : filterClasses) {
            Table filterTableAnnotation = filterClass.getAnnotation(Table.class);
            if (filterTableAnnotation != null) {
                message.append(filterTableAnnotation.name()).append(", ");
            } else {
                message.append(filterClass.getSimpleName()).append(", "); // Fallback to class name
            }
        }

        // Remove trailing comma and space
        if (filterClasses.length > 0) {
            message.setLength(message.length() - 2);
        }
        return message.toString();
    }
}

