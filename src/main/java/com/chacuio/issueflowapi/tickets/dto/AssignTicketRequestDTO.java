package com.chacuio.issueflowapi.tickets.dto;

import java.util.UUID;

public record AssignTicketRequestDTO(
        UUID ticketId,
        UUID assignedId
        // el id del usuario que hizo la asignación va en el header, se lee de ahí

        //Seguir con esto: Revisar bien cada uno de los dto y mappers de user y ticket.
        // luego queda hacer el servicio y sus implementaciones + toda la logica de negocio
) { }
