package org.example1.aibasedstartupideavalidator.ai.client;

import org.example1.aibasedstartupideavalidator.validation.dto.ValidationRequest;

public interface LLMClient {

    String analyzeStartup(ValidationRequest request);
}