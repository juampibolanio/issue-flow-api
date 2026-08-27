package com.chacuio.issueflowapi.tickets.exception;

import java.util.UUID;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(UUID id) {
        super("Ticket with id: " + id + " not found");
    }
}
