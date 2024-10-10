package com.example.demo.exception;

import lombok.NoArgsConstructor;

/**
 * Runtime exception that will be thrown when a user tries to update/delete
 * a client that doesn't exist
 *
 * @author Christian Jácome
 */
@NoArgsConstructor
public class NoSuchClientExistsException extends
        RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public NoSuchClientExistsException(String message) {
        super(message);
    }
}
