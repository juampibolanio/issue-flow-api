package com.chacuio.issueflowapi.tickets.dto;

import java.util.UUID;

public record AssignTicketRequestDTO(
        UUID ticketId,
        UUID assignedId
        // el id del usuario que hizo la asignación va en el header, se lee de ahí
) { }
