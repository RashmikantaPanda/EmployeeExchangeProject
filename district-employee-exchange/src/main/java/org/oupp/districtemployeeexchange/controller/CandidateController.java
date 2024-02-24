package org.oupp.districtemployeeexchange.controller;


import org.oupp.districtemployeeexchange.dto.CandidateEditRequest;
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

@RestController
@RequestMapping("candidate")
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

//    @PostMapping("/login")
//    public ResponseEntity<Boolean> authenticateCandidate(@RequestBody LoginRequest loginRequest) {
//        if (candidateService.loginCandidate(loginRequest.getEmail(), loginRequest.getPassword()))
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/login")
    public String AuthenticateAndGetToken(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.GenerateToken(loginRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Rashmikanta";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Integer candidateId) {
        Candidate candidate = candidateService.getCandidateById(candidateId);
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
