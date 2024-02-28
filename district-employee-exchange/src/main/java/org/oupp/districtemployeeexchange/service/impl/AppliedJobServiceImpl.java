package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.repository.AppliedJobRepository;
import org.oupp.districtemployeeexchange.service.AppliedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppliedJobServiceImpl implements AppliedJobService {

    @Autowired
    AppliedJobRepository appliedJobRepository;
    @Override
    public AppliedJob applyJob(AppliedJob appliedJob) {
        return appliedJobRepository.save(appliedJob);
    }

    @Override
    public AppliedJob changeStatus(Integer appliedJobId, Boolean status) {
        Optional<AppliedJob> appliedJob=appliedJobRepository.findById(appliedJobId);
        if(appliedJob.isPresent()){
            appliedJob.get().setIsAccepted(status);
             return appliedJobRepository.save(appliedJob.get());
        }
        else
            return null;
    }
}
