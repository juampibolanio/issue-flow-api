package com.chacuio.issueflowapi.common.exception.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ErrorResponseDTO{
    private String message;
    private int status;
    private String path;
    private Instant timestamp;
}
