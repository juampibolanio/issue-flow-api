package com.chacuio.issueflowapi.tickets.controller;

import com.chacuio.issueflowapi.tickets.dto.AssignTicketRequestDTO;
import com.chacuio.issueflowapi.tickets.dto.ChangeTicketStateDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketRequestDTO;
import com.chacuio.issueflowapi.tickets.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService service;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<TicketDTO> assign(
            @PathVariable UUID id,
            @Valid @RequestBody AssignTicketRequestDTO dto,
            @RequestHeader("X-User-Id") UUID assignerId) {
        return ResponseEntity.ok(service.assign(id, dto, assignerId));
    }

    @PatchMapping("/{id}/change-state")
    public ResponseEntity<TicketDTO> changeState(
            @PathVariable UUID id,
            @Valid @RequestBody ChangeTicketStateDTO dto,
            @RequestHeader("X-User-Id") UUID assignerId) {
        return ResponseEntity.ok(service.changeState(id, dto, assignerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
