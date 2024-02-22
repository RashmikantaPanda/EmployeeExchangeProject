package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Override
    public Candidate registerCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public Boolean loginCandidate(String email, String password) {
        return null;
    }

    @Override
    public Candidate getCandidateByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public Candidate getCandidateByEmail(String email) {
        return null;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        return null;
    }
}
