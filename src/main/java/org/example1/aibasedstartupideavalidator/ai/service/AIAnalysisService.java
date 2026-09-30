package org.example1.aibasedstartupideavalidator.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example1.aibasedstartupideavalidator.ai.client.LLMClient;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationRequest;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationResponse;
import org.example1.aibasedstartupideavalidator.validation.entity.ValidationReport;
import org.example1.aibasedstartupideavalidator.validation.repository.ValidationReportRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIAnalysisService {

    private final LLMClient llmClient;

    private final ObjectMapper objectMapper;

    private final ValidationReportRepository validationReportRepository;

    public ValidationResponse analyzeStartup(
            ValidationRequest request) {

        try {

            // 1. Send startup idea to Gemini
            String aiResponse =
                    llmClient.analyzeStartup(request);

            System.out.println("Gemini Response:");
            System.out.println(aiResponse);

            // 2. Convert Gemini JSON into ValidationResponse
            ValidationResponse response =
                    objectMapper.readValue(
                            aiResponse,
                            ValidationResponse.class
                    );

            // 3. Make sure startup name comes from request
            response.setStartupName(
                    request.getStartupName()
            );

            // 4. Save AI validation report to MySQL
            ValidationReport report =
                    ValidationReport.builder()
                            .startupName(
                                    response.getStartupName()
                            )
                            .overallScore(
                                    response.getOverallScore()
                            )
                            .marketScore(
                                    response.getMarketScore()
                            )
                            .problemScore(
                                    response.getProblemScore()
                            )
                            .competitionScore(
                                    response.getCompetitionScore()
                            )
                            .businessModelScore(
                                    response.getBusinessModelScore()
                            )
                            .verdict(
                                    response.getVerdict()
                            )
                            .summary(
                                    response.getSummary()
                            )
                            .strengths(
                                    response.getStrengths()
                            )
                            .weaknesses(
                                    response.getWeaknesses()
                            )
                            .recommendations(
                                    response.getRecommendations()
                            )
                            .build();

            ValidationReport savedReport =
                    validationReportRepository.save(report);

            // 5. Put generated database ID into response
            response.setValidationId(
                    savedReport.getId()
            );

            // 6. Return AI result to React
            return response;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to process Gemini AI response",
                    e
            );
        }
    }
}