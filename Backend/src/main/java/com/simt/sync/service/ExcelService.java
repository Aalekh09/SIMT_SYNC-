package com.simt.sync.service;

import com.simt.sync.entity.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.simt.sync.dto.DashboardResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelService {

    public byte[] exportStudents(List<Student> students) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Students");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Student ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Course");
            header.createCell(3).setCellValue("Fees");

            int rowNum = 1;

            for (Student s : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getStudentId());
                row.createCell(1).setCellValue(s.getName());
                row.createCell(2).setCellValue(s.getCourse());
                row.createCell(3).setCellValue(s.getTotalFees());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Excel Error");
        }
    }

    public byte[] exportDashboard(DashboardResponse d) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Dashboard");

            int rowNum = 0;

            Row r1 = sheet.createRow(rowNum++);
            r1.createCell(0).setCellValue("Total Students");
            r1.createCell(1).setCellValue(d.getTotalStudents());

            Row r2 = sheet.createRow(rowNum++);
            r2.createCell(0).setCellValue("Total Collection");
            r2.createCell(1).setCellValue(d.getTotalCollection());

            Row r3 = sheet.createRow(rowNum++);
            r3.createCell(0).setCellValue("Monthly Revenue");
            r3.createCell(1).setCellValue(d.getMonthlyRevenue());

            Row r4 = sheet.createRow(rowNum++);
            r4.createCell(0).setCellValue("Total Pending");
            r4.createCell(1).setCellValue(d.getTotalPending());

            Row r5 = sheet.createRow(rowNum++);
            r5.createCell(0).setCellValue("Monthly Pending");
            r5.createCell(1).setCellValue(d.getMonthlyPending());

            Row r6 = sheet.createRow(rowNum++);
            r6.createCell(0).setCellValue("Total Enquiries");
            r6.createCell(1).setCellValue(d.getTotalEnquiries());

            Row r7 = sheet.createRow(rowNum++);
            r7.createCell(0).setCellValue("Converted Enquiries");
            r7.createCell(1).setCellValue(d.getConvertedEnquiries());

            Row r8 = sheet.createRow(rowNum++);
            r8.createCell(0).setCellValue("Pending Enquiries");
            r8.createCell(1).setCellValue(d.getPendingEnquiries());

            Row r9 = sheet.createRow(rowNum++);
            r9.createCell(0).setCellValue("Admissions This Month");
            r9.createCell(1).setCellValue(d.getAdmissionsThisMonth());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Excel Error");
        }
    }
}