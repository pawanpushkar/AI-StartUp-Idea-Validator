package org.example1.aibasedstartupideavalidator.validation.service;

import lombok.RequiredArgsConstructor;
import org.example1.aibasedstartupideavalidator.ai.service.AIAnalysisService;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationRequest;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final AIAnalysisService aiAnalysisService;

    public ValidationResponse validateStartup(ValidationRequest request) {

        return aiAnalysisService.analyzeStartup(request);
    }
}