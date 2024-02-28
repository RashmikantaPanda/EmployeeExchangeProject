package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.dto.ApplyJobRequest;
import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.repository.AppliedJobRepository;
import org.oupp.districtemployeeexchange.service.AppliedJobService;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppliedJobServiceImpl implements AppliedJobService {

    @Autowired
    AppliedJobRepository appliedJobRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private EmployerService employerService;

    @Override
    public String applyJob(ApplyJobRequest applyJobRequest) {
        Candidate candidate = candidateService.getCandidateById(applyJobRequest.getCandidateId());
//        Employer employer = employerService.getEmployerById(applyJobRequest.getEmployerId());
        Jobs jobs = jobService.getJobsById(applyJobRequest.getJobId());
        AppliedJob appliedJob = new AppliedJob();
        if (candidate != null) {
//            if (employer != null) {
                if (jobs != null) {
                    appliedJob.setCandidate(candidate);
//                    appliedJob.setEmployer(employer);
                    appliedJob.setJob(jobs);
                    appliedJob.setAppliedDate(applyJobRequest.getAppliedDate());
                    appliedJobRepository.save(appliedJob);
                    return "Applied";
                }
//            }
        }
        return "Something went wrong";
    }

    @Override
    public AppliedJob changeStatus(Integer appliedJobId, Boolean status) {
        Optional<AppliedJob> appliedJob = appliedJobRepository.findById(appliedJobId);
        if (appliedJob.isPresent()) {
            appliedJob.get().setIsAccepted(status);
            return appliedJobRepository.save(appliedJob.get());
        } else
            return null;
    }
}
