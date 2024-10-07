package com.example.demo.exception;

import lombok.NoArgsConstructor;
/**
 * Runtime exception that will be thrown when a user tries to save a client
 * with a username or email that already exists
 *
 * @author Christian Jácome
 */
@NoArgsConstructor
public class ClientAlreadyExistsException extends RuntimeException {

    private String message;

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ClientAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
