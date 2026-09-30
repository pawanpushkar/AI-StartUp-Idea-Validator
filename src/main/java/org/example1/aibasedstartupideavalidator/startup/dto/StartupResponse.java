package org.example1.aibasedstartupideavalidator.startup.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupResponse {

    private Long id;

    private String startupName;

    private String idea;

    private String problem;

    private String targetCustomer;

    private String market;

    private String businessModel;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}