package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.AppliedJob;

public interface AppliedJobService {
    AppliedJob applyJob(AppliedJob appliedJob);
    AppliedJob changeStatus(Integer id,Boolean status);
}
