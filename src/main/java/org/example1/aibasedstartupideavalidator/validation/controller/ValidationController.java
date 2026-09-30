package org.example1.aibasedstartupideavalidator.validation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationRequest;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationResponse;
import org.example1.aibasedstartupideavalidator.validation.service.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/validations")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping
    public ResponseEntity<ValidationResponse> validateStartup(
            @Valid @RequestBody ValidationRequest request) {

        ValidationResponse response =
                validationService.validateStartup(request);

        return ResponseEntity.ok(response);
    }
}