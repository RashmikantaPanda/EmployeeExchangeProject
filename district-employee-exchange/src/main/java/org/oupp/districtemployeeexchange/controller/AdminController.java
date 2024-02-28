package org.oupp.districtemployeeexchange.controller;

import org.oupp.districtemployeeexchange.dto.JwtResponseDTO;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Admin;
import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.security.JwtService;
import org.oupp.districtemployeeexchange.service.AdminService;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.registerAdmin(admin), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.authenticateUser(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String helloAdmin() {
        return "Hello Admin";
    }

    /*For Employer*/
    @PostMapping("/employer/register")
    public ResponseEntity<Employer> registerEmployer(@RequestBody Employer employer) {
        return new ResponseEntity<>(employerService.registerEmployer(employer), HttpStatus.CREATED);
    }

    @GetMapping("/employers/all")
    public ResponseEntity<List<Employer>> getAllEmployer() {
        return new ResponseEntity<>(employerService.getAllEmployer(), HttpStatus.OK);
    }

    /*For Candidates*/
    @GetMapping("/candidates/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.getAllCandidate(), HttpStatus.OK);
    }

}
