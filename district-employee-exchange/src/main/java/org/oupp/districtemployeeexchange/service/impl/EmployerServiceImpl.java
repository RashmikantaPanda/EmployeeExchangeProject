package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.entity.Role;
import org.oupp.districtemployeeexchange.repository.EmployerRepository;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.JobService;
import org.oupp.districtemployeeexchange.service.RoleService;
import org.oupp.districtemployeeexchange.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    public EmployerRepository employerRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JobService jobService;

    @Override
    public Employer registerEmployer(Employer employer) {
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        employerRepository.save(employer);

        Set<Role> roles = roleService.getOrCreateRoles(UserRole.ROLE_EMPLOYER);
        employer.setRoles(roles);
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
    public Employer getEmployerById(Integer id) {
        Optional<Employer> employer = employerRepository.findById(id);
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

    @Override
    public Employer editAndSaveEmployer(Employer employer, Integer id) {
        return null;
    }

    @Override
    public Jobs createNewJob(Jobs job) {
        System.out.println(job);
        System.out.println("Emp id : "+job.getEmployer().getId());
        Optional<Employer> employer = employerRepository.findById(job.getEmployer().getId());
        if (employer.isPresent()) {
            job.setEmployer(employer.get());
            return jobService.createNewJob(job);
        } else {
            return null;
        }
    }

    @Override
    public List<Jobs> createMultipleNewJobs(List<Jobs> jobs) {
        return jobService.createNewJobs(jobs);
    }


}
