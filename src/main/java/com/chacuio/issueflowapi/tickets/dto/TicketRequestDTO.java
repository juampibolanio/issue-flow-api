package com.chacuio.issueflowapi.tickets.dto;

import com.chacuio.issueflowapi.tickets.model.Priority;
import com.chacuio.issueflowapi.tickets.model.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TicketRequestDTO(
        @NotBlank(message = "The title field cannot be null")
        String title,
        String description,
        State state,
        Priority priority,
        UUID assignedId,

        @NotNull(message = "The reporter id field cannot be null")
        UUID reporterId
) { }
