package org.oupp.districtemployeeexchange.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.oupp.districtemployeeexchange.util.Address;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employer")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
public class Employer extends Users {

    @Size(min = 2, max = 50, message = "Organization should be between 2 and 50 ")
    String organizationName;

    @Embedded
    Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "employer",fetch = FetchType.EAGER)
//    @JsonBackReference
    @JsonIgnoreProperties("employer")
    List<Jobs> jobs;
}
