package com.aftermidnight.aftermidnight.controllers;

import com.aftermidnight.aftermidnight.dtos.BarmakerCreateRequest;
import com.aftermidnight.aftermidnight.dtos.BarmakerLoginRequest;
import com.aftermidnight.aftermidnight.dtos.BarmakerResponseDTO;
import com.aftermidnight.aftermidnight.entities.Barmaker;
import com.aftermidnight.aftermidnight.mappers.BarmakerMapper;
import com.aftermidnight.aftermidnight.services.BarmakerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/barmakers")
@RequiredArgsConstructor
public class BarmakerController {

    private final BarmakerService barmakerService;
    private final BarmakerMapper barmakerMapper;

    @PostMapping
    public ResponseEntity<BarmakerResponseDTO> register(@Valid @RequestBody BarmakerCreateRequest request) {
        Barmaker barmaker = barmakerService.register(request);
        BarmakerResponseDTO response = barmakerMapper.toResponse(barmaker);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<BarmakerResponseDTO> login(@Valid @RequestBody BarmakerLoginRequest request) {
        Barmaker barmaker = barmakerService.login(request);
        return ResponseEntity.ok(barmakerMapper.toResponse(barmaker));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarmakerResponseDTO> getBarmaker(@PathVariable UUID id) {
        Barmaker barmaker = barmakerService.getBarmaker(id);
        return ResponseEntity.ok(barmakerMapper.toResponse(barmaker));
    }
}