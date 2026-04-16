package com.interviewflow.api.application;

import com.interviewflow.api.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService service;

    @PostMapping
    public JobApplicationResponse create(@Valid @RequestBody JobApplicationRequest request,
                                         @AuthenticationPrincipal User user) {
        return service.create(request, user);
    }

    @GetMapping
    public List<JobApplicationResponse> getByUser(@AuthenticationPrincipal User user) {
        return service.getByUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal User user) {
        service.delete(id, user);
    }

    @PutMapping("/{id}")
    public JobApplicationResponse update(@PathVariable Long id,
                                         @RequestBody JobApplicationRequest request,
                                         @AuthenticationPrincipal User user) {
        return service.update(id, request, user);
    }
}