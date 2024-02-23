package org.oupp.districtemployeeexchange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.oupp.districtemployeeexchange.util.Address;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name = "user_id")
public class Candidate extends Users {

    @Size(min = 2, max = 15, message = "Firstname should be between 2 and 15 ")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
    String firstName;

    @Size(min = 2, max = 15, message = "Lastname should be between 2 and 15 ")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must not contain numbers or special characters")
    String lastName;

    @Size(min = 10, max = 10, message = "Mobile no length should be exactly 10")
    @Pattern(regexp = "^\\d{10}$")
    String mobile;

    LocalDate dateOfBirth;

    @Embedded
    Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    List<Qualification> qualifications;
}
