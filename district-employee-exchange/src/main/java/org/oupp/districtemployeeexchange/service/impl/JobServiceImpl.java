package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.repository.JobRepository;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
//    @Autowired
//    EmployerService employerService;

    @Override
    public Jobs createNewJob(Jobs job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Jobs> createNewJobs(List<Jobs> jobs) {
        List<Jobs> createdJobs = new ArrayList<>();
        for (Jobs j : jobs) {
            createdJobs.add(jobRepository.save(j));
        }
        return createdJobs;
    }

    @Override
    public Jobs getJobsById(Integer jobId) {
        Optional<Jobs> job = jobRepository.findById(jobId);
        return job.orElse(null);
    }

    @Override
    public List<Jobs> getAllAvailableJobs() {
        return jobRepository.getJobsByStatusIsTrue();
    }

    @Override
    public List<Jobs> getJobsByOrganization(String organizationName) {
        return jobRepository.getJobsByEmployer_OrganizationName(organizationName);
    }

    @Override
    public List<Jobs> searchJobs(String searchItem) {
        return jobRepository.searchJobsByAllFields(searchItem);
    }

    public List<Jobs> searchJobByDescriptionOrTypeOrLocation(String title,String desc, String type, String location) {
        return jobRepository.findJobsByJobTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrJobTypeContainingIgnoreCaseOrJobLocationContainingIgnoreCase(title,desc, type, location);
    }
}
