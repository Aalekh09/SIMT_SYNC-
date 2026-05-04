package com.simt.sync.dto;

import lombok.Data;

@Data
public class DashboardResponse {

    private long totalStudents;
    private double totalCollection;

    private double monthlyRevenue;
    private double totalPending;
    private double monthlyPending;

    private long totalEnquiries;
    private long convertedEnquiries;
    private long pendingEnquiries;

    private long admissionsThisMonth;
}