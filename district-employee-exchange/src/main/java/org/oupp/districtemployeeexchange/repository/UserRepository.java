package org.oupp.districtemployeeexchange.repository;

import org.oupp.districtemployeeexchange.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> getUsersByEmail(String email);

    Optional<Users> getUsersByEmailAndPassword(String email,String password);
}
