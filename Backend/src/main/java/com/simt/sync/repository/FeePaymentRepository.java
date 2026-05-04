package com.simt.sync.repository;

import com.simt.sync.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {

    List<FeePayment> findByStudentId(Long studentId);

    List<FeePayment> findByStudentNameContainingIgnoreCase(String name);

    FeePayment findBySlipNumber(String slipNumber);
}