package org.example1.aibasedstartupideavalidator.startup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example1.aibasedstartupideavalidator.startup.dto.CreateStartupRequest;
import org.example1.aibasedstartupideavalidator.startup.dto.StartupResponse;
import org.example1.aibasedstartupideavalidator.startup.dto.UpdateStartupRequest;
import org.example1.aibasedstartupideavalidator.startup.service.StartupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/startups")
@RequiredArgsConstructor
public class StartupController {

    private final StartupService startupService;


    // CREATE
    @PostMapping
    public ResponseEntity<StartupResponse> createStartup(
            @Valid @RequestBody CreateStartupRequest request) {

        StartupResponse response =
                startupService.createStartup(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET ALL
    @GetMapping
    public ResponseEntity<List<StartupResponse>> getAllStartups() {

        return ResponseEntity.ok(
                startupService.getAllStartups()
        );
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StartupResponse> getStartupById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                startupService.getStartupById(id)
        );
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StartupResponse> updateStartup(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStartupRequest request) {

        StartupResponse response =
                startupService.updateStartup(id, request);

        return ResponseEntity.ok(response);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStartup(
            @PathVariable Long id) {

        startupService.deleteStartup(id);

        return ResponseEntity.noContent().build();
    }
}