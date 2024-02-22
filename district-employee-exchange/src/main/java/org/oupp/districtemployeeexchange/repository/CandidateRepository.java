package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {

}
