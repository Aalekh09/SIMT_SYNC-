package com.simt.sync.controller;

import com.simt.sync.entity.FeePayment;
import com.simt.sync.entity.Student;
import com.simt.sync.repository.FeePaymentRepository;
import com.simt.sync.repository.StudentRepository;
import com.simt.sync.service.FeeService;
import com.simt.sync.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    // ➕ Add payment
    @PostMapping("/pay")
    public FeePayment pay(@RequestBody FeePayment payment) {
        return feeService.addPayment(payment);
    }

    // 📋 Ledger
    @GetMapping("/ledger/{studentId}")
    public List<FeePayment> ledger(@PathVariable Long studentId) {
        return feeService.getLedger(studentId);
    }

    // 🔍 Search by name
    @GetMapping("/search")
    public List<FeePayment> search(@RequestParam String name) {
        return feeService.searchByName(name);
    }

    // 🔍 Search by slip
    @GetMapping("/slip")
    public FeePayment slip(@RequestParam String slip) {
        return feeService.searchBySlip(slip);
    }

    // 📊 Paid
    @GetMapping("/paid/{studentId}")
    public Double paid(@PathVariable Long studentId) {
        return feeService.getTotalPaid(studentId);
    }

    // 📊 Pending
    @GetMapping("/pending/{studentId}")
    public Double pending(@PathVariable Long studentId) {
        return feeService.getPending(studentId);
    }

    @Autowired
    private PdfService pdfService;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private FeePaymentRepository feeRepo;

    @GetMapping("/slip/pdf/{paymentId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long paymentId) {

        FeePayment payment = feeRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Student student = studentRepo.findById(payment.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        byte[] pdf = pdfService.generateFeeSlip(payment, student);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=fee-slip.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}