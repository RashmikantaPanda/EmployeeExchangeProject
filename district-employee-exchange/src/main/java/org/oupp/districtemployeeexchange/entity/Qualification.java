package org.oupp.districtemployeeexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qualifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String standard;
    String institution;
    String stream;
    String board;
    Double percentage;
    Integer admissionYear;
    Integer passoutYear;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Candidate candidate;
}
