package org.example1.aibasedstartupideavalidator.common.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private int status;

    private String error;

    private String message;

    private String path;

    private LocalDateTime timestamp;
}