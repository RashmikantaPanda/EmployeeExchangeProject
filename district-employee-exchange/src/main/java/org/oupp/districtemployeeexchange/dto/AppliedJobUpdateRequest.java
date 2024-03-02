package org.oupp.districtemployeeexchange.dto;

import lombok.Data;

@Data
public class AppliedJobUpdateRequest {
    Integer appliedJobId;
    Boolean isAccepted;
    String rejectReason;
}
