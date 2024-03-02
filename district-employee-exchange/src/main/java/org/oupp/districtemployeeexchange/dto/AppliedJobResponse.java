package org.oupp.districtemployeeexchange.dto;

import lombok.Data;
import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Jobs;

@Data
public class AppliedJobResponse {
    Integer appliedJobId;
    Integer candidateId;
    Boolean isAccepted;
    String rejectReason;
    Jobs job;
}
