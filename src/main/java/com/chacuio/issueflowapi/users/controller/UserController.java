package com.chacuio.issueflowapi.users.controller;

import com.chacuio.issueflowapi.users.dto.UserDTO;
import com.chacuio.issueflowapi.users.dto.UserRequestDTO;
import com.chacuio.issueflowapi.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.create(dto));
    }
}
