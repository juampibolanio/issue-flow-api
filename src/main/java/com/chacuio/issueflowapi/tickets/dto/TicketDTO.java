package com.chacuio.issueflowapi.tickets.dto;

import com.chacuio.issueflowapi.tickets.model.Priority;
import com.chacuio.issueflowapi.tickets.model.State;
import com.chacuio.issueflowapi.users.dto.UserSummaryDTO;

import java.time.Instant;
import java.util.UUID;

public record TicketDTO(
        UUID id,
        String title,
        String description,
        State state,
        Priority priority,
        boolean isActive,
        UserSummaryDTO assignee,
        UserSummaryDTO reporter,
        Instant createdAt,
        Instant updatedAt
) { }
