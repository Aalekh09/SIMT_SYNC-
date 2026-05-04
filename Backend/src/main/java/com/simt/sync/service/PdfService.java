package com.simt.sync.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.simt.sync.entity.FeePayment;
import com.simt.sync.entity.Student;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateFeeSlip(FeePayment payment, Student student) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 12);

            // 🏫 Title
            document.add(new Paragraph("SIMT Institute Fee Receipt", titleFont));
            document.add(new Paragraph(" "));

            // 👤 Student Info
            document.add(new Paragraph("Student Name: " + student.getName(), normal));
            document.add(new Paragraph("Student ID: " + student.getStudentId(), normal));
            document.add(new Paragraph("Course: " + student.getCourse(), normal));

            document.add(new Paragraph(" "));

            // 💰 Payment Info
            document.add(new Paragraph("Amount Paid: ₹" + payment.getAmountPaid(), normal));
            document.add(new Paragraph("Payment Method: " + payment.getPaymentMethod(), normal));
            document.add(new Paragraph("Installment: " + payment.getInstallment(), normal));
            document.add(new Paragraph("Slip Number: " + payment.getSlipNumber(), normal));
            document.add(new Paragraph("Date: " + payment.getPaymentDate(), normal));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Authorized Signature"));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("PDF Error: " + e.getMessage());
        }

        return out.toByteArray();
    }
}