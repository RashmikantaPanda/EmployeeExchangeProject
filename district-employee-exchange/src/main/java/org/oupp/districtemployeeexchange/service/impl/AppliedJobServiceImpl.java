package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.dto.AppliedJobResponse;
import org.oupp.districtemployeeexchange.dto.AppliedJobUpdateRequest;
import org.oupp.districtemployeeexchange.dto.ApplyJobRequest;
import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.repository.AppliedJobRepository;
import org.oupp.districtemployeeexchange.service.AppliedJobService;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

//    @Override
//    public List<Jobs> getAppliedJobsByCandidateId(Integer candiDateId) {
//        List<AppliedJob> appliedJobs = appliedJobRepository.getAppliedJobByCandidate_Id(candiDateId);
//        List<Jobs> jobs = new ArrayList<>();
//
//        for (AppliedJob appliedJob : appliedJobs) {
//            Jobs job = appliedJob.getJob();
//            jobs.add(job);
//        }
//        return jobs;
//    }

    @Override
    public List<AppliedJobResponse> getAppliedJobsByCandidateId(Integer candiDateId) {
        List<AppliedJob> appliedJobs = appliedJobRepository.getAppliedJobByCandidate_Id(candiDateId);
        List<Jobs> jobs = new ArrayList<>();

        List<AppliedJobResponse> appliedJobResponses=new ArrayList<>();
         for (AppliedJob aj : appliedJobs) {
            AppliedJobResponse appliedJobResponse=new AppliedJobResponse();
            appliedJobResponse.setAppliedJobId(aj.getAppliedJobId());
            appliedJobResponse.setCandidateId(aj.getCandidate().getId());
            appliedJobResponse.setIsAccepted(aj.getIsAccepted());
            appliedJobResponse.setRejectReason(aj.getRejectionReason());
            appliedJobResponse.setJob(jobService.getJobsById(aj.getJob().getJobId()));

            appliedJobResponses.add(appliedJobResponse);
        }

         return appliedJobResponses;
    }


    @Override
    public List<AppliedJob> getAppliedJobsByEmployeeId(Integer employeeId){
        List<AppliedJob> appliedJobs=appliedJobRepository.getAppliedJobByJob_Employer_Id(employeeId);

//        for(AppliedJob aj:appliedJobs){
//
//        }
        return appliedJobs;
    }

    @Override
    public AppliedJob acceptOrRejectCandidate(AppliedJobUpdateRequest appliedJobUpdateRequest) {

        Optional<AppliedJob> appliedJob = appliedJobRepository.findById(appliedJobUpdateRequest.getAppliedJobId());
        if (appliedJob.isPresent()) {
            appliedJob.get().setIsAccepted(appliedJobUpdateRequest.getIsAccepted());
            appliedJob.get().setRejectionReason(appliedJobUpdateRequest.getRejectReason());

           return appliedJobRepository.save(appliedJob.get());
        }

        return null;
    }
}
