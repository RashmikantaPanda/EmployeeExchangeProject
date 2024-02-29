package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob,Integer> {

    List<AppliedJob> getAppliedJobByCandidate_Id(Integer id);

}
