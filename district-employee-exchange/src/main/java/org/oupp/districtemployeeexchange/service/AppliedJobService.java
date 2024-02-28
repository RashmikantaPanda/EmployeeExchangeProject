package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.dto.ApplyJobRequest;
import org.oupp.districtemployeeexchange.entity.AppliedJob;

public interface AppliedJobService {
    String applyJob(ApplyJobRequest applyJobRequest);

    AppliedJob changeStatus(Integer id, Boolean status);
}
