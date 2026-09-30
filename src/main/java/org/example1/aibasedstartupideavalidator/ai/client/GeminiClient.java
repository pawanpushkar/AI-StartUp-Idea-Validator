package org.example1.aibasedstartupideavalidator.ai.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example1.aibasedstartupideavalidator.validation.dto.ValidationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class GeminiClient implements LLMClient {

    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    public GeminiClient(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;

        this.restClient = RestClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }

    @Override
    public String analyzeStartup(ValidationRequest request) {

        String prompt = buildPrompt(request);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        String response = restClient.post()
                .uri(
                        "/v1beta/models/{model}:generateContent",
                        model
                )
                .header("x-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(String.class);

        return extractText(response);
    }

    private String buildPrompt(ValidationRequest request) {

        return """
                You are an expert startup analyst and startup validation consultant.

                Analyze the following startup idea realistically.

                Startup Name:
                %s

                Startup Idea:
                %s

                Problem:
                %s

                Target Customer:
                %s

                Market:
                %s

                Business Model:
                %s

                Evaluate the startup using these criteria:

                1. Overall startup potential
                2. Market potential
                3. Problem strength
                4. Competition
                5. Business model

                Give every score between 0 and 100.

                Consider:

                - Market demand
                - Customer pain
                - Target customer clarity
                - Existing competitors
                - Competitive differentiation
                - Customer acquisition difficulty
                - Revenue potential
                - Scalability
                - Business model viability
                - Major risks

                Be realistic.
                Do not give high scores simply because the idea sounds interesting.

                The verdict must be exactly one of:

                EXCELLENT
                PROMISING
                NEEDS_WORK
                RISKY
                NOT_RECOMMENDED

                Return ONLY valid JSON.

                Do NOT use Markdown.
                Do NOT use ```json.
                Do NOT add any explanation outside the JSON.

                Return exactly this JSON structure:

                {
                  "overallScore": 0,
                  "marketScore": 0,
                  "problemScore": 0,
                  "competitionScore": 0,
                  "businessModelScore": 0,
                  "verdict": "PROMISING",
                  "summary": "",
                  "strengths": "",
                  "weaknesses": "",
                  "recommendations": ""
                }

                """.formatted(
                request.getStartupName(),
                request.getIdea(),
                request.getProblem(),
                request.getTargetCustomer(),
                request.getMarket(),
                request.getBusinessModel()
        );
    }

    private String extractText(String response) {

        try {

            JsonNode root =
                    objectMapper.readTree(response);

            JsonNode textNode = root
                    .path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text");

            if (textNode.isMissingNode() ||
                    textNode.asText().isBlank()) {

                throw new RuntimeException(
                        "Gemini response did not contain generated text"
                );
            }

            return cleanJson(textNode.asText());

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse Gemini response",
                    e
            );
        }
    }

    private String cleanJson(String text) {

        String cleaned = text.trim();

        // Gemini sometimes returns ```json ... ```
        if (cleaned.startsWith("```json")) {
            cleaned = cleaned.substring(7).trim();
        }

        // Handle plain ```
        else if (cleaned.startsWith("```")) {
            cleaned = cleaned.substring(3).trim();
        }

        if (cleaned.endsWith("```")) {
            cleaned = cleaned.substring(
                    0,
                    cleaned.length() - 3
            ).trim();
        }

        return cleaned;
    }
}