package com.simt.sync.controller;

import com.simt.sync.dto.DashboardResponse;
import com.simt.sync.repository.StudentRepository;
import com.simt.sync.service.DashboardService;
import com.simt.sync.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard() {
        return dashboardService.getDashboard();
    }


    @Autowired
    private ExcelService excelService;

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/export/students")
    public ResponseEntity<byte[]> exportStudents() {

        byte[] excel = excelService.exportStudents(studentRepo.findAll());

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=students.xlsx")
                .body(excel);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportDashboard() {

        DashboardResponse data = dashboardService.getDashboard();

        byte[] excel = excelService.exportDashboard(data);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=dashboard.xlsx")
                .body(excel);
    }

}