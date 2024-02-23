package org.oupp.districtemployeeexchange.dto;

import lombok.Data;
import org.oupp.districtemployeeexchange.entity.Candidate;

@Data
public class CandidateEditRequest {
    Candidate candidate;
    Integer id;
}
