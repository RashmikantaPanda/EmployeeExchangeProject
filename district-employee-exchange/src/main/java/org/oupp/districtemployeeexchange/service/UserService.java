package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.dto.JwtResponseDTO;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Users;

import java.util.List;

public interface UserService {
    JwtResponseDTO authenticateUser(LoginRequest loginRequest);

    Users registerUser(Users user);

    Users getUserByEmailId(String email);

    Users getUserByEmailAndPassword(String email, String password);

    List<Users> getAllUsers();

}
