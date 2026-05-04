package com.simt.sync.repository;

import com.simt.sync.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

    List<Enquiry> findByStudentNameContainingIgnoreCase(String name);

    List<Enquiry> findByEnquiryDateBetween(LocalDate start, LocalDate end);
}