package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.dto.ApplyJobRequest;
import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Jobs;

import java.util.List;

public interface AppliedJobService {
    String applyJob(ApplyJobRequest applyJobRequest);

    AppliedJob changeStatus(Integer id, Boolean status);
    public List<Jobs> getAppliedJobsByCandidateId(Integer candiDateId);
}
