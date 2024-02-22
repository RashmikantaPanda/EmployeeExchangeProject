package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Employer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployerService {
    Employer registerEmployer(Employer employer);
    Boolean loginEmployer(String email,String password);
    Employer getEmployerByEmailAndPassword(String email,String password);
    Employer getEmployerByEmail(String email);
    Employer getEmployerByOrganization(String organization);
    List<Employer> getAllEmployer();
}
