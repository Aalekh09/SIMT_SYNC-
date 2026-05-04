package com.simt.sync.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class EnquiryCallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long enquiryId;

    private LocalDate callDate;

    private String feedback;

    private String nextFollowUpDate;
}