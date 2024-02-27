package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Jobs;

import java.util.List;

public interface JobService {
    Jobs createNewJob(Jobs job);
    List<Jobs>  createNewJobs(List<Jobs> jobs);

    Jobs getJobsById(Integer jobId);

    List<Jobs> getJobsByOrganization(String organizationName);

    List<Jobs> searchJobs(String searchItem);
}
