package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Users;

import java.util.List;

public interface UserService {
    Users registerUser(Users user);

    Users getUserByEmailId(String email);

    Users getUserByEmailAndPassword(String email, String password);

    List<Users> getAllUsers();

}
