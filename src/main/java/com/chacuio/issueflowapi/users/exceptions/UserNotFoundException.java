package com.chacuio.issueflowapi.users.exceptions;

import com.chacuio.issueflowapi.common.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class UserNotFoundException extends ResourceNotFoundException {
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
