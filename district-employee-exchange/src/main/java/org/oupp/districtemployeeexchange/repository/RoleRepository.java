package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Role;
import org.oupp.districtemployeeexchange.util.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> getRoleByName(UserRole name);
}
