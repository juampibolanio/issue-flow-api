package com.chacuio.issueflowapi.tickets.exception;

import com.chacuio.issueflowapi.common.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class TicketNotFoundException extends ResourceNotFoundException {
    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(UUID id) {
        super("Ticket with id: " + id + " not found");
    }
}
