package com.simt.sync.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 Custom Student ID
    private String studentId;

    private String name;
    private String fatherName;
    private String motherName;

    private String session;
    private String batchTiming; // 1hr / 2hr

    private LocalDate dateOfBirth;
    private LocalDate admissionDate;

    private String email;

    private String phone;
    private String emergencyPhone;

    private String address;
    private String pinCode;

    private String course;

    private Double totalFees;

    private String photoUrl;
    private String marksheetUrl;
    private String aadharUrl;

    private String teacherName;

    private Long enquiryId; // optional link
}
