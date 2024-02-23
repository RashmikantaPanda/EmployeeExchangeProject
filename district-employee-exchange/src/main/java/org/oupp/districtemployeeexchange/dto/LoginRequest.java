package org.oupp.districtemployeeexchange.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
