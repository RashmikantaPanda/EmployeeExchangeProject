package org.oupp.districtemployeeexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applied_jobs")
public class AppliedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applied_job_id")
    private Integer appliedJobId;

    @ManyToOne
    @JoinColumn(name = "employer_user_id")
    @JsonIgnore
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "candidate_user_id")
    @JsonIgnore
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    private Boolean isAccepted;
    private String rejectionReason;
    private LocalDateTime appliedDate;
}
