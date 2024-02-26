package org.oupp.districtemployeeexchange.controller;


import org.oupp.districtemployeeexchange.dto.CandidateEditRequest;
import org.oupp.districtemployeeexchange.dto.DemoJson;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.security.JwtService;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("candidate")
//@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody Candidate candidate) {
        return new ResponseEntity<>(candidateService.registerCandidate(candidate), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginRequest.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }


    @GetMapping("/hello")
    public ResponseEntity<DemoJson> hello(){
        DemoJson demoJson=new DemoJson();
        demoJson.setData("Hello Rashmikanta");
        return new ResponseEntity<>(demoJson,HttpStatus.OK);
    }
    @GetMapping("/home")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "CANDIDATE HOME");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Integer candidateId) {
        Candidate candidate = candidateService.getCandidateById(candidateId);
        if (candidate != null)
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Candidate> getCandidateByEmail(@PathVariable("email") String email) {
        Candidate candidate = candidateService.getCandidateByEmail(email);
        if (candidate != null)
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Candidate> editCandidate(@PathVariable("id") Integer id) {
        Candidate candidate = candidateService.getCandidateById(id);
        if (candidate != null)
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/edit/save")
    public ResponseEntity<Candidate> editCandidate(@RequestBody CandidateEditRequest candidateEditRequest) {
        Candidate candidate = candidateService.editCandidate(candidateEditRequest.getCandidate(),candidateEditRequest.getId());
        if (candidate != null)
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


}
