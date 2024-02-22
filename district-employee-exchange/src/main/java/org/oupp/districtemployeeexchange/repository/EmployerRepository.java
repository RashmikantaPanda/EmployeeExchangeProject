package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    Optional<Employer> getEmployerByOrganizationName(String organizationName);

    Optional<Employer> getEmployerByEmailAndPassword(String email,String password);

    Optional<Employer> getEmployerByEmail(String email);


}
