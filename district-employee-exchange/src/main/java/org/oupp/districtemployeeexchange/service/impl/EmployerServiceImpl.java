package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.repository.EmployerRepository;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    public EmployerRepository employerRepository;

    @Override
    public Employer registerEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public Boolean loginEmployer(String email, String password) {
        Optional<Employer> employer = employerRepository.getEmployerByEmailAndPassword(email, password);
        return employer.isPresent();
    }

    @Override
    public Employer getEmployerByEmailAndPassword(String email, String password) {
        Optional<Employer> employer = employerRepository.getEmployerByEmailAndPassword(email, password);
        return employer.orElse(null);
    }

    @Override
    public Employer getEmployerByEmail(String email) {
        Optional<Employer> employer = employerRepository.getEmployerByEmail(email);
        return employer.orElse(null);
    }

    @Override
    public Employer getEmployerByOrganization(String organization) {
        Optional<Employer> employer = employerRepository.getEmployerByOrganizationName(organization);
        return employer.orElse(null);
    }

    @Override
    public List<Employer> getAllEmployer() {
        return employerRepository.findAll();
    }
}
