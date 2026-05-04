package com.simt.sync.service;

import com.simt.sync.entity.*;
import com.simt.sync.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private EnquiryCallLogRepository callLogRepository;

    // ✅ Add enquiry
    public Enquiry addEnquiry(Enquiry enquiry) {
        enquiry.setStatus(Status.PENDING);
        enquiry.setEnquiryDate(LocalDate.now());
        return enquiryRepository.save(enquiry);
    }

    // ❌ Delete enquiry
    public void deleteEnquiry(Long id) {
        enquiryRepository.deleteById(id);
    }

    // 🔄 Update status
    public Enquiry updateStatus(Long id, Status status) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));

        enquiry.setStatus(status);
        return enquiryRepository.save(enquiry);
    }

    // 📞 Add call log
    public EnquiryCallLog addCallLog(EnquiryCallLog log) {
        log.setCallDate(LocalDate.now());
        return callLogRepository.save(log);
    }

    // 📋 Get all enquiries
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    public List<Enquiry> searchByName(String name) {
        return enquiryRepository.findByStudentNameContainingIgnoreCase(name);
    }

    public List<Enquiry> filterByDate(LocalDate start, LocalDate end) {
        return enquiryRepository.findByEnquiryDateBetween(start, end);
    }


}