package com.simt.sync.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String fatherName;
    private String phone;
    private String address;
    private String course;

    private LocalDate enquiryDate;

    private String remarks;

    @Enumerated(EnumType.STRING)
    private Status status;
}