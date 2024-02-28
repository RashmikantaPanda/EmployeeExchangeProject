package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob,Integer> {

}
