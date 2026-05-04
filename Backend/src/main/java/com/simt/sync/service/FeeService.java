package com.simt.sync.service;

import com.simt.sync.entity.*;
import com.simt.sync.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeeService {

    @Autowired
    private FeePaymentRepository feeRepo;

    @Autowired
    private StudentRepository studentRepo;

    // 💰 Add payment
    public FeePayment addPayment(FeePayment payment) {

        Student student = studentRepo.findById(payment.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        payment.setStudentName(student.getName());
        payment.setPaymentDate(LocalDate.now());

        return feeRepo.save(payment);
    }

    // 📋 Get ledger (all payments of student)
    public List<FeePayment> getLedger(Long studentId) {
        return feeRepo.findByStudentId(studentId);
    }

    // 🔍 Search by name
    public List<FeePayment> searchByName(String name) {
        return feeRepo.findByStudentNameContainingIgnoreCase(name);
    }

    // 🔍 Search by slip
    public FeePayment searchBySlip(String slip) {
        return feeRepo.findBySlipNumber(slip);
    }

    // 📊 Calculate total paid
    public Double getTotalPaid(Long studentId) {
        return feeRepo.findByStudentId(studentId)
                .stream()
                .mapToDouble(FeePayment::getAmountPaid)
                .sum();
    }

    // 📊 Pending fees
    public Double getPending(Long studentId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        double paid = getTotalPaid(studentId);

        return student.getTotalFees() - paid;
    }
}