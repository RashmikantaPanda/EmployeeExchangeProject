package org.oupp.districtemployeeexchange.controller;

import org.oupp.districtemployeeexchange.dto.EmployerEditRequest;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobService jobService;

//    @PostMapping("/register")
//    public ResponseEntity<Employer> registerEmployer(@RequestBody Employer employer){
//        return new ResponseEntity<>(employerService.registerEmployer(employer), HttpStatus.CREATED);
//    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> authenticateEmployer(@RequestBody LoginRequest loginRequest){
        if(employerService.loginEmployer(loginRequest.getEmail(), loginRequest.getPassword()))
            return new ResponseEntity<>(true,HttpStatus.OK);
        else
            return new ResponseEntity<>(false,HttpStatus.OK);

    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Employer> editEmployer(@PathVariable("id") Integer id){
        Employer employer=employerService.getEmployerById(id);
        if(employer!=null)
            return new ResponseEntity<>(employer,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/edit/save")
    public ResponseEntity<Employer> editAndSaveEmployer(@RequestBody EmployerEditRequest employerEditRequest){
        Employer employer=employerService.editAndSaveEmployer(employerEditRequest.getEmployer(),employerEditRequest.getId());
        if(employer!=null)
            return new ResponseEntity<>(employer,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add/job")
    public ResponseEntity<Jobs> addNewJob(@RequestBody Jobs job){
        return new ResponseEntity<>(jobService.createNewJob(job),HttpStatus.CREATED);
    }

    @PostMapping("/add/jobs")
    public ResponseEntity<List<Jobs>> addMultipleJobs(@RequestBody List<Jobs> jobs){
        return new ResponseEntity<>(jobService.createNewJobs(jobs),HttpStatus.CREATED);
    }


}
