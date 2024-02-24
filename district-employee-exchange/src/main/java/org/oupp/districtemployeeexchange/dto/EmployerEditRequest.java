package org.oupp.districtemployeeexchange.dto;

import lombok.Data;
import org.oupp.districtemployeeexchange.entity.Employer;

@Data
public class EmployerEditRequest {
    Employer employer;
    Integer id;
}
