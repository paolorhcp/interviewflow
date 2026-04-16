package com.interviewflow.api.application;

public class JobApplicationMapper {

    private JobApplicationMapper() {
    }

    public static JobApplication toEntity(JobApplicationRequest request) {
        return JobApplication.builder()
                .company(request.getCompany())
                .position(request.getPosition())
                .status(request.getStatus())
                .applicationDate(request.getApplicationDate())
                .build();
    }

    public static JobApplicationResponse toResponse(JobApplication entity) {
        return JobApplicationResponse.builder()
                .id(entity.getId())
                .company(entity.getCompany())
                .position(entity.getPosition())
                .status(entity.getStatus())
                .applicationDate(entity.getApplicationDate())
                .build();
    }
}