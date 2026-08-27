package com.chacuio.issueflowapi.tickets.exception;

import java.util.UUID;

public class TicketAlreadyClosed extends RuntimeException {
    public TicketAlreadyClosed(String message) {
        super(message);
    }

    public TicketAlreadyClosed(UUID id) {
        super("Ticket with id " + id + " has a closed status");
    }
}
