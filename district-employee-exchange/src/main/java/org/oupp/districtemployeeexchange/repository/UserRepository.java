package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {

}
