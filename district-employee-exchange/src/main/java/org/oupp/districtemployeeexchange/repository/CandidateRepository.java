package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    Optional<Candidate> getCandidateByEmail(String email);
    Optional<Candidate> getCandidateByEmailAndPassword(String email,String password);

}
