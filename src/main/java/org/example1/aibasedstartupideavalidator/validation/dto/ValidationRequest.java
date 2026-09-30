package org.example1.aibasedstartupideavalidator.validation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationRequest {

    @NotBlank(message = "Startup name is required")
    @Size(max = 150, message = "Startup name must not exceed 150 characters")
    private String startupName;

    @NotBlank(message = "Startup idea is required")
    private String idea;

    @NotBlank(message = "Problem statement is required")
    private String problem;

    @NotBlank(message = "Target customer is required")
    private String targetCustomer;

    @NotBlank(message = "Market is required")
    private String market;

    @NotBlank(message = "Business model is required")
    private String businessModel;
}