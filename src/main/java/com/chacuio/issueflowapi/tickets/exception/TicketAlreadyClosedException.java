package com.chacuio.issueflowapi.tickets.exception;

import java.util.UUID;

public class TicketAlreadyClosedException extends RuntimeException {
    public TicketAlreadyClosedException(String message) {
        super(message);
    }

    public TicketAlreadyClosedException(UUID id) {
        super("Ticket with id " + id + " has a closed status");
    }
}
