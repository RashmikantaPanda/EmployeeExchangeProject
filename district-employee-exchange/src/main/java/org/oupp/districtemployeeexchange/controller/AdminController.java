package org.oupp.districtemployeeexchange.controller;

import org.oupp.districtemployeeexchange.dto.LoginRequest;
import org.oupp.districtemployeeexchange.entity.Admin;
import org.oupp.districtemployeeexchange.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){
        return new ResponseEntity<>(adminService.registerAdmin(admin), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> authenticateAdmin(@RequestBody LoginRequest loginRequest){
        if(adminService.loginAdmin(loginRequest.getEmail(),loginRequest.getPassword()))
            return new ResponseEntity<>(true,HttpStatus.OK);
        else
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }
}
