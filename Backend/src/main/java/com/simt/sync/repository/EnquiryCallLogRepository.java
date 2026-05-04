package com.simt.sync.repository;

import com.simt.sync.entity.EnquiryCallLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryCallLogRepository extends JpaRepository<EnquiryCallLog, Long> {
}