package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    Optional<Employer> getEmployerByOrganizationName(String organizationName);

}
