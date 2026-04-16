package com.interviewflow.api.application;

import com.interviewflow.api.exception.ResourceNotFoundException;
import com.interviewflow.api.exception.UnauthorizedException;
import com.interviewflow.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationResponse create(JobApplicationRequest request, User user) {
        JobApplication entity = JobApplicationMapper.toEntity(request);
        entity.setUser(user);

        if (entity.getApplicationDate() == null) {
            entity.setApplicationDate(LocalDate.now());
        }

        JobApplication saved = repository.save(entity);
        return JobApplicationMapper.toResponse(saved);
    }

    public List<JobApplicationResponse> getByUser(User user) {
        return repository.findByUserId(user.getId())
                .stream()
                .map(JobApplicationMapper::toResponse)
                .toList();
    }

    public void delete(Long id, User user) {
        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (!application.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You cannot delete this application");
        }

        repository.delete(application);
    }

    public JobApplicationResponse update(Long id, JobApplicationRequest request, User user) {

        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (!application.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You cannot update this application");
        }

        application.setCompany(request.getCompany());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());

        if (request.getApplicationDate() != null) {
            application.setApplicationDate(request.getApplicationDate());
        }

        JobApplication updated = repository.save(application);

        return JobApplicationMapper.toResponse(updated);
    }
}