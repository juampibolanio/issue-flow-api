package com.chacuio.issueflowapi.users.dto;

import java.util.UUID;

public record UserSummaryDTO(
        UUID id,
        String name,
        boolean isActive
) { }
