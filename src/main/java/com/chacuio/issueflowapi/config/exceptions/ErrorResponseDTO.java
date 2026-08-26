package com.chacuio.issueflowapi.config.exceptions;

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
