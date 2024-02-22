package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Users registerUser(Users user);
    Users getUserByEmailId(String email);
    Users getUserByEmailAndPassword(String email, String password);
    List<Users> getAllUsers();

}
