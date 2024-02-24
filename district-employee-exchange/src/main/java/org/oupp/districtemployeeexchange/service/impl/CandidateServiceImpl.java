package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Role;
import org.oupp.districtemployeeexchange.repository.CandidateRepository;
import org.oupp.districtemployeeexchange.repository.RoleRepository;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.oupp.districtemployeeexchange.service.RoleService;
import org.oupp.districtemployeeexchange.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Candidate registerCandidate(Candidate candidate) {

        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        candidateRepository.save(candidate);

        Set<Role> roles = roleService.getOrCreateRoles(UserRole.ROLE_CANDIDATE);
        candidate.setRoles(roles);
        return candidateRepository.save(candidate);
    }

    @Override
    public Boolean loginCandidate(String email, String password) {
        Optional<Candidate> candidate = candidateRepository.getCandidateByEmailAndPassword(email, password);
        return candidate.isPresent();
    }

    @Override
    public Candidate getCandidateById(Integer candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        return candidate.orElse(null);
    }

    @Override
    public Candidate getCandidateByEmailAndPassword(String email, String password) {
        Optional<Candidate> candidate = candidateRepository.getCandidateByEmailAndPassword(email, password);
        return candidate.orElse(null);
    }

    @Override
    public Candidate getCandidateByEmail(String email) {
        Optional<Candidate> candidate = candidateRepository.getCandidateByEmail(email);
        return candidate.orElse(null);
    }

    @Override
    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate editCandidate(Candidate candidate, Integer id) {
        Optional<Candidate> oldCandidate = candidateRepository.findById(id);
        if (oldCandidate.isPresent()) {
            oldCandidate.get().setFirstName(candidate.getFirstName());
            oldCandidate.get().setLastName(candidate.getLastName());
            oldCandidate.get().setMobile(candidate.getMobile());
            oldCandidate.get().setDateOfBirth(candidate.getDateOfBirth());
            oldCandidate.get().setAddress(candidate.getAddress());
            oldCandidate.get().setQualifications(candidate.getQualifications());

            return candidateRepository.save(oldCandidate.get());
        }
        return null;
    }

}
