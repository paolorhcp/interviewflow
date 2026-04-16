package com.interviewflow.api.application;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class JobApplicationResponse {

    private Long id;
    private String company;
    private String position;
    private String status;
    private LocalDate applicationDate;
}