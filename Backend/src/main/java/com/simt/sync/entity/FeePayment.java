package com.simt.sync.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class FeePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private String studentName;

    private Double amountPaid;

    private String paymentMethod; // CASH / UPI / BANK

    private String installment; // 1st / 2nd / Full

    private String slipNumber;

    private LocalDate paymentDate;
}