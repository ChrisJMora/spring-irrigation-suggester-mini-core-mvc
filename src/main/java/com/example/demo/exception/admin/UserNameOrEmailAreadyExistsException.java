package com.example.demo.exception.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Runtime exception that will be thrown when a user tries to save a client
 * with a username or email that already exists
 *
 * @author Christian Jácome
 */
@NoArgsConstructor
@Getter
public class UserNameOrEmailAreadyExistsException extends
        RuntimeException {

    private Map<String, String> details;

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserNameOrEmailAreadyExistsException(String message){
        super(message);
    }

    public UserNameOrEmailAreadyExistsException(String message,
                                                Map<String, String> details)
    {
        super(message);
        this.details = details;
    }
}
