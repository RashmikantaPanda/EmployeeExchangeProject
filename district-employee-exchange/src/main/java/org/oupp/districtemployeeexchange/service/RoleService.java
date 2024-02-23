package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Role;
import org.oupp.districtemployeeexchange.util.UserRole;

import java.util.Set;

public interface RoleService {
    public Set<Role> getOrCreateRoles(UserRole... roles);
}
