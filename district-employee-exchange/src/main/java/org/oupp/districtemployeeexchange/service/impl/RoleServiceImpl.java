package org.oupp.districtemployeeexchange.service.impl;

import org.oupp.districtemployeeexchange.entity.Role;
import org.oupp.districtemployeeexchange.repository.RoleRepository;
import org.oupp.districtemployeeexchange.service.RoleService;
import org.oupp.districtemployeeexchange.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Set<Role> getOrCreateRoles(UserRole... roleNames) {
        Set<Role> roles = new HashSet<>();
        for (UserRole roleName : roleNames) {
            Optional<Role> roleOptional = roleRepository.getRoleByName(roleName);
            if (roleOptional.isPresent()) {
                roles.add(roleOptional.get());
            } else {
                Role newRole = new Role(roleName);
                roles.add(roleRepository.save(newRole));
            }
        }
        return roles;
    }
}
