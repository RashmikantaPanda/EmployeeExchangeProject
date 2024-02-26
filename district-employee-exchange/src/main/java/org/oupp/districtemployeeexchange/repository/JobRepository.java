package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Jobs,Integer> {
    List<Jobs> findJobsByDescriptionContainingIgnoreCaseOrJobTypeContainingIgnoreCaseOrJobLocationContainingIgnoreCase(
            String description, String jobType, String jobLocation);
}
