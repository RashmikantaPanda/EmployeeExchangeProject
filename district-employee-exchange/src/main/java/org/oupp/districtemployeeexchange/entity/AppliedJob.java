package org.oupp.districtemployeeexchange.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
//    @JsonManagedReference
    @JoinColumn(name = "employer_user_id")
    @JsonIgnoreProperties("appliedJobs")
    private Employer employer;


    @ManyToOne
//    @JsonManagedReference
    @JoinColumn(name = "candidate_user_id")
    @JsonIgnoreProperties("appliedJobs")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    private Boolean isAccepted;
    private String rejectionReason;
    private LocalDateTime appliedDate;
}
