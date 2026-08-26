package com.chacuio.issueflowapi.users.dto;

import com.chacuio.issueflowapi.users.model.Role;

import java.time.Instant;
import java.util.UUID;

public record UserDTO (
        UUID id,
        String name,
        String email,
        Role role,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt
){}
