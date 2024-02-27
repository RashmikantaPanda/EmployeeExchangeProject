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
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.registerAdmin(admin), HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public String AuthenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(loginRequest.getEmail());
//        } else {
//            throw new UsernameNotFoundException("invalid user request..!!");
//        }
//    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            System.out.println("Login Success");
            String token = jwtService.generateToken(loginRequest.getEmail());
            System.out.println("Token Generated");
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
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
