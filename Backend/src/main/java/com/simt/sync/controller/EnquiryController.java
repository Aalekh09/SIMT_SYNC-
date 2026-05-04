package com.simt.sync.controller;

import com.simt.sync.entity.*;
import com.simt.sync.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    // ➕ Add enquiry
    @PreAuthorize("hasAnyRole('ADMIN','RECEPTION')")
    @PostMapping("/add")
    public Enquiry add(@RequestBody Enquiry enquiry) {
        return enquiryService.addEnquiry(enquiry);
    }

    // ❌ Delete enquiry
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        return "Deleted successfully";
    }

    // 🔄 Update status
    @PutMapping("/{id}/status")
    public Enquiry updateStatus(@PathVariable Long id,
                                @RequestParam Status status) {
        return enquiryService.updateStatus(id, status);
    }

    // 📞 Add call log
    @PostMapping("/call-log")
    public EnquiryCallLog addCallLog(@RequestBody EnquiryCallLog log) {
        return enquiryService.addCallLog(log);
    }

    // 📋 Get all enquiries
    @GetMapping("/all")
    public List<Enquiry> getAll() {
        return enquiryService.getAllEnquiries();
    }

    @GetMapping("/search")
    public List<Enquiry> search(@RequestParam String name) {
        return enquiryService.searchByName(name);
    }

    @GetMapping("/filter")
    public List<Enquiry> filter(
            @RequestParam String start,
            @RequestParam String end) {

        return enquiryService.filterByDate(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}