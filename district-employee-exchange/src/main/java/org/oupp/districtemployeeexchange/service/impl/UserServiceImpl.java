package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Users;
import org.oupp.districtemployeeexchange.repository.UserRepository;
import org.oupp.districtemployeeexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    public UserRepository userRepository;

    @Override
    public Users registerUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserByEmailId(String email) {
        Optional<Users> users= userRepository.getUsersByEmail(email);
        return users.orElse(null);
    }

    @Override
    public Users getUserByEmailAndPassword(String email, String password) {
        Optional<Users> users=userRepository.getUsersByEmailAndPassword(email, password);
        return users.orElse(null);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
