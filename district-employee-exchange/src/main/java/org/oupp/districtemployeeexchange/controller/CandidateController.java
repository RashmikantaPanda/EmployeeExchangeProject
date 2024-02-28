package org.oupp.districtemployeeexchange.controller;


import org.oupp.districtemployeeexchange.dto.*;
import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Candidate;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.security.JwtService;
import org.oupp.districtemployeeexchange.service.AppliedJobService;
import org.oupp.districtemployeeexchange.service.CandidateService;
import org.oupp.districtemployeeexchange.service.JobService;
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
@RequestMapping("candidate")
//@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private AppliedJobService appliedJobService;
    @Autowired
    private JobService jobService;



    @PostMapping("/register")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody Candidate candidate) {
        return new ResponseEntity<>(candidateService.registerCandidate(candidate), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.authenticateUser(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<DemoJson> hello() {
        DemoJson demoJson = new DemoJson();
        demoJson.setData("Hello Rashmikanta");
        return new ResponseEntity<>(demoJson, HttpStatus.OK);
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

    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidate();
        if (candidates != null)
            return new ResponseEntity<>(candidates, HttpStatus.OK);
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
        Candidate candidate = candidateService.editCandidate(candidateEditRequest.getCandidate(), candidateEditRequest.getId());
        if (candidate != null)
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/job/apply")
    public ResponseEntity<Map<String,String>> applyJob(@RequestBody ApplyJobRequest applyJobRequest){
        Map<String,String > response=new HashMap<>();
        String status=appliedJobService.applyJob(applyJobRequest);
        response.put("status",status);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/job/available")
    public ResponseEntity<List<Jobs>> viewAllAvailableJobs(){
        return new ResponseEntity<>(jobService.getAllAvailableJobs(),HttpStatus.OK);
    }
}
