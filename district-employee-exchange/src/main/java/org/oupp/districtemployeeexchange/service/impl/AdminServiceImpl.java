package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Admin;
import org.oupp.districtemployeeexchange.entity.Role;
import org.oupp.districtemployeeexchange.repository.AdminRepository;
import org.oupp.districtemployeeexchange.repository.RoleRepository;
import org.oupp.districtemployeeexchange.service.AdminService;
import org.oupp.districtemployeeexchange.service.RoleService;
import org.oupp.districtemployeeexchange.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleService roleService;

    public Admin registerAdmin(Admin admin){
//        Set<Role> roles=new HashSet<>(Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_CREATOR")));
//        admin.setRoles(roles);

        Set<Role> roles = roleService.getOrCreateRoles(UserRole.ROLE_ADMIN, UserRole.ROLE_CREATOR);
        admin.setRoles(roles);
        return adminRepository.save(admin);
    }

    @Override
    public Boolean loginAdmin(String email, String password) {
        Optional<Admin> admin=adminRepository.getAdminByEmailAndPassword(email,password);
        return admin.isPresent();
    }

}

