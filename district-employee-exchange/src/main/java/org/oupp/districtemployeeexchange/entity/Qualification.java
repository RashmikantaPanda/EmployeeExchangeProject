package org.oupp.districtemployeeexchange.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Qualification {
    String standard;
    String institution;
    String stream;
    String board;
    Double percentage;
    Integer admissionYear;
    Integer passwordYear;
}
