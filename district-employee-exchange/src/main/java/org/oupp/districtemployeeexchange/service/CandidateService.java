package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate registerCandidate(Candidate candidate);
    Boolean loginCandidate(String email,String password);
    Candidate getCandidateByEmailAndPassword(String email,String password);
    Candidate getCandidateByEmail(String email);
    List<Candidate> getAllCandidate();
}
