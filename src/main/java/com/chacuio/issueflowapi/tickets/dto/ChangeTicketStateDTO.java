package com.chacuio.issueflowapi.tickets.dto;

import com.chacuio.issueflowapi.tickets.model.State;
import jakarta.validation.constraints.NotNull;

public record ChangeTicketStateDTO(
        @NotNull(message = "The new state is required")
        State state
) { }
