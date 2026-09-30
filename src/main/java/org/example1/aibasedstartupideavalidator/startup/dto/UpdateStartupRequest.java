package org.example1.aibasedstartupideavalidator.startup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStartupRequest {

    @NotBlank(message = "Startup name is required")
    @Size(max = 150, message = "Startup name must not exceed 150 characters")
    private String startupName;

    @NotBlank(message = "Startup idea is required")
    private String idea;

    @NotBlank(message = "Problem statement is required")
    private String problem;

    @NotBlank(message = "Target customer is required")
    @Size(max = 255, message = "Target customer must not exceed 255 characters")
    private String targetCustomer;

    @NotBlank(message = "Market is required")
    @Size(max = 150, message = "Market must not exceed 150 characters")
    private String market;

    @NotBlank(message = "Business model is required")
    @Size(max = 100, message = "Business model must not exceed 100 characters")
    private String businessModel;
}