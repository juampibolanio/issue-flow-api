package com.chacuio.issueflowapi.tickets.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignTicketRequestDTO(
        @NotNull(message = "The assignee ID is required")
        UUID assigneeId
        // the id of the user making the assignment goes in the request header
) { }
