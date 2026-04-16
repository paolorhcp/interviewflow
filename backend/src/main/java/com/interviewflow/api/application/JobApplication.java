package com.interviewflow.api.application;

import com.interviewflow.api.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    private String position;

    private String status;

    private LocalDate applicationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}