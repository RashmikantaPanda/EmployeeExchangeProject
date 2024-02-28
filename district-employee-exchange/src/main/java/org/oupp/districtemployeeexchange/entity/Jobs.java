package org.oupp.districtemployeeexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    Integer jobId;
    String jobTitle;
    String description;
    String jobType;
    String jobLocation;
    Double salary;
    Integer noOfVacancy;
    Boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    Employer employer;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppliedJob> appliedJobs;
    private Boolean deleted=false;
}
