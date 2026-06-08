package com.example.demo.controller;

import com.example.demo.dto.CreateRequestDTO;
import com.example.demo.dto.TransitionDTO;
import com.example.demo.model.Request;
import com.example.demo.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Request> create(@Valid @RequestBody CreateRequestDTO dto) {
        return ResponseEntity.ok(service.createRequest(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Request> transition(@PathVariable Long id, @RequestBody TransitionDTO dto) {
        return ResponseEntity.ok(service.updateLifecycle(id, dto.getStatus(), dto.getOperatorId()));
    }
}