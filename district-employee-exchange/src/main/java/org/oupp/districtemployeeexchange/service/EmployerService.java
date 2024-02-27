package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.entity.Jobs;

import java.util.List;

public interface EmployerService {
    Employer registerEmployer(Employer employer);

    Boolean loginEmployer(String email, String password);

    Employer getEmployerByEmailAndPassword(String email, String password);

    Employer getEmployerById(Integer id);

    Employer getEmployerByEmail(String email);

    Employer getEmployerByOrganization(String organization);

    List<Employer> getAllEmployer();

    Employer editAndSaveEmployer(Employer employer, Integer id);

    Jobs createNewJob(Jobs job);

    List<Jobs> createMultipleNewJobs(List<Jobs> jobs);
}