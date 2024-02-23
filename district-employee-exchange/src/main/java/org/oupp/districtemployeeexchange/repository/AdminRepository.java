package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Optional<Admin> getAdminByEmailAndPassword(String email, String password);

}
