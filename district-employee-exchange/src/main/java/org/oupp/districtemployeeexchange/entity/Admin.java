package org.oupp.districtemployeeexchange.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(name="user_id")
public class Admin extends Users{
    @Size(min = 2, max = 30, message = "Admin name should be between 2 and 15 ")
    String name;
}
