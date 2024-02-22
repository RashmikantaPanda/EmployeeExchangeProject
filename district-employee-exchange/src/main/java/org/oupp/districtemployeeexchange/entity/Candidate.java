package org.oupp.districtemployeeexchange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Size(min = 2, max = 15, message = "Firstname should be between 2 and 15 ")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
    String firstName;

    @Size(min = 2, max = 15, message = "Lastname should be between 2 and 15 ")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must not contain numbers or special characters")
    String lastName;

    @Email
    @Column(unique = true, nullable = false)
    String email;

    @Size(min = 6, max = 20, message = "Password length should be between 6 and 20")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$", message = "Password length should be between 6 and 20")
    String password;

    @Size(min = 10, max = 10, message = "Mobile no length should be exactly 10")
    @Pattern(regexp = "^\\d{10}$")
    String mobile;

    LocalDate dateOfBirth;

    @Embedded
    Address address;

    @Embedded
    Qualification qualification;

}
