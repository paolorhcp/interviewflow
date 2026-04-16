package com.interviewflow.api.application;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobApplicationRequest {

    @NotBlank
    private String company;

    @NotBlank
    private String position;

    @NotBlank
    private String status;

    private LocalDate applicationDate;
}