package org.oupp.districtemployeeexchange.controller;

import org.oupp.districtemployeeexchange.dto.AppliedJobUpdateRequest;
import org.oupp.districtemployeeexchange.dto.EmployerEditRequest;
import org.oupp.districtemployeeexchange.dto.JwtResponseDTO;
import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.AppliedJob;
import org.oupp.districtemployeeexchange.entity.Employer;
import org.oupp.districtemployeeexchange.entity.Jobs;
import org.oupp.districtemployeeexchange.service.AppliedJobService;
import org.oupp.districtemployeeexchange.service.EmployerService;
import org.oupp.districtemployeeexchange.service.JobService;
import org.oupp.districtemployeeexchange.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private AppliedJobService appliedJobService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.authenticateUser(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Employer> editEmployer(@PathVariable("id") Integer id) {
        Employer employer = employerService.getEmployerById(id);
        if (employer != null)
            return new ResponseEntity<>(employer, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/edit/save")
    public ResponseEntity<Employer> editAndSaveEmployer(@RequestBody EmployerEditRequest employerEditRequest) {
        Employer employer = employerService.editAndSaveEmployer(employerEditRequest.getEmployer(), employerEditRequest.getId());
        if (employer != null)
            return new ResponseEntity<>(employer, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add/job")
    public ResponseEntity<Jobs> addNewJob(@RequestBody Jobs job) {
        return new ResponseEntity<>(employerService.createNewJob(job), HttpStatus.CREATED);
    }

    @PostMapping("/add/jobs")
    public ResponseEntity<List<Jobs>> addMultipleJobs(@RequestBody List<Jobs> jobs) {
        return new ResponseEntity<>(jobService.createNewJobs(jobs), HttpStatus.CREATED);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Employer> getEmployerByID(@PathVariable("id") Integer id){
        return new ResponseEntity<>(employerService.getEmployerById(id),HttpStatus.OK);
    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<Employer> getEmployerByEmail(@PathVariable("email") String emailId) {
        return new ResponseEntity<>(employerService.getEmployerByEmail(emailId), HttpStatus.OK);
    }

    @PostMapping("/update/appliedJob")
    public ResponseEntity<AppliedJob> updateAppliedJobStatus(@RequestBody AppliedJobUpdateRequest appliedJobUpdateRequest){
        System.out.println(appliedJobUpdateRequest);
        return new ResponseEntity<>(appliedJobService.acceptOrRejectCandidate(appliedJobUpdateRequest),HttpStatus.OK);
    }

    @GetMapping("/view/appliedJob/{id}")
    public ResponseEntity<List<AppliedJob>> viewAllAppliedJobs(@PathVariable("id") Integer empId){
        return new ResponseEntity<>(appliedJobService.getAppliedJobsByEmployeeId(empId),HttpStatus.OK);
    }



}
