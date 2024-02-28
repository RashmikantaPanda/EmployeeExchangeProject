package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Jobs,Integer> {
    List<Jobs> findJobsByJobTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrJobTypeContainingIgnoreCaseOrJobLocationContainingIgnoreCase(
            String jobTitle,String description, String jobType, String jobLocation);

    @Query("SELECT j FROM Jobs j WHERE " +
            "LOWER(j.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.jobType) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.jobLocation) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(j.employer.organizationName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Jobs> searchJobsByAllFields(String searchTerm);

    List<Jobs> getJobsByEmployer_OrganizationName(String organizationName);

    List<Jobs> getJobsByStatusIsTrue();
}
