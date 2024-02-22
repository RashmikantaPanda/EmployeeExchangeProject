package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Employer;

import java.util.List;

public interface EmployerService {
    Employer registerEmployer(Employer employer);

    Boolean loginEmployer(String email, String password);

    Employer getEmployerByEmailAndPassword(String email, String password);

    Employer getEmployerByEmail(String email);

    Employer getEmployerByOrganization(String organization);

    List<Employer> getAllEmployer();
}
