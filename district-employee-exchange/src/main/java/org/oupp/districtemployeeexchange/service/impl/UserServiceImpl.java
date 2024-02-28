package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.dto.JwtResponseDTO;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Users;
import org.oupp.districtemployeeexchange.repository.UserRepository;
import org.oupp.districtemployeeexchange.security.JwtService;
import org.oupp.districtemployeeexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public JwtResponseDTO authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            JwtResponseDTO token=new JwtResponseDTO();
            token.setToken(jwtService.generateToken(loginRequest.getEmail()));
            return token;
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

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
