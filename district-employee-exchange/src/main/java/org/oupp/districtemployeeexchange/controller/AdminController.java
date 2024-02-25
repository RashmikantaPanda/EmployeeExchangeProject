package org.oupp.districtemployeeexchange.controller;

import org.oupp.districtemployeeexchange.dto.JwtResponseDTO;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Admin;
import org.oupp.districtemployeeexchange.security.JwtService;
import org.oupp.districtemployeeexchange.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){
        return new ResponseEntity<>(adminService.registerAdmin(admin), HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Boolean> authenticateAdmin(@RequestBody LoginRequest loginRequest){
//        if(adminService.loginAdmin(loginRequest.getEmail(),loginRequest.getPassword()))
//            return new ResponseEntity<>(true,HttpStatus.OK);
//        else
//            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/login")
    public String AuthenticateAndGetToken(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping("/hello")
    public String helloAdmin(){
        return "Hello Admin";
    }

}
