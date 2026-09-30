package org.example1.aibasedstartupideavalidator.validation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationResponse {

    private Long validationId;

    private String startupName;

    private Integer overallScore;

    private Integer marketScore;

    private Integer problemScore;

    private Integer competitionScore;

    private Integer businessModelScore;

    private String verdict;

    private String summary;

    private String strengths;

    private String weaknesses;

    private String recommendations;
}