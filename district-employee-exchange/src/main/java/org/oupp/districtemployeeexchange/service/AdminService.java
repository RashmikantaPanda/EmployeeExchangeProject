package org.oupp.districtemployeeexchange.service;

import org.oupp.districtemployeeexchange.entity.Admin;

public interface AdminService {
    public Admin registerAdmin(Admin admin);
    public Boolean loginAdmin(String email,String password);
}
