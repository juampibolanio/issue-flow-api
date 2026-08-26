package com.chacuio.issueflowapi.users.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        this("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(UUID id) {
        this("User with ID: " + id + " not found");
    }
}
