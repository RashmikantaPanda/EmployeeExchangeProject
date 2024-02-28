package org.oupp.districtemployeeexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobRequest {
      Integer employerId;
      Integer candidateId;
      Integer jobId;
      LocalDateTime appliedDate=LocalDateTime.now();
}
