package com.simt.sync.service;

import com.simt.sync.dto.DashboardResponse;
import com.simt.sync.entity.*;
import com.simt.sync.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private FeePaymentRepository feeRepo;

    @Autowired
    private EnquiryRepository enquiryRepo;

    public DashboardResponse getDashboard() {

        DashboardResponse res = new DashboardResponse();

        LocalDate startMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate now = LocalDate.now();

        // 👥 Total Students
        res.setTotalStudents(studentRepo.count());

        // 💰 Total Collection
        double totalCollection = feeRepo.findAll()
                .stream()
                .mapToDouble(FeePayment::getAmountPaid)
                .sum();
        res.setTotalCollection(totalCollection);

        // 📊 Monthly Revenue
        double monthlyRevenue = feeRepo.findAll()
                .stream()
                .filter(p -> p.getPaymentDate().isAfter(startMonth.minusDays(1)))
                .mapToDouble(FeePayment::getAmountPaid)
                .sum();
        res.setMonthlyRevenue(monthlyRevenue);

        // 📊 Pending Fees
        double totalPending = studentRepo.findAll()
                .stream()
                .mapToDouble(s -> {
                    double total = (s.getTotalFees() != null) ? s.getTotalFees() : 0;
                    double paid = getPaid(s.getId());
                    return total - paid;
                })
                .sum();
        res.setTotalPending(totalPending);

        // 📊 Monthly Pending
        double monthlyPending = studentRepo.findAll()
                .stream()
                .filter(s -> s.getAdmissionDate().isAfter(startMonth.minusDays(1)))
                .mapToDouble(s -> {
                    double total = (s.getTotalFees() != null) ? s.getTotalFees() : 0;
                    double paid = getPaid(s.getId());
                    return total - paid;
                })
                .sum();
        res.setMonthlyPending(monthlyPending);

        // 📞 Enquiry Stats
        List<Enquiry> enquiries = enquiryRepo.findAll();

        res.setTotalEnquiries(enquiries.size());
        res.setConvertedEnquiries(
                enquiries.stream().filter(e -> e.getStatus() == Status.CONVERTED).count()
        );
        res.setPendingEnquiries(
                enquiries.stream().filter(e -> e.getStatus() == Status.PENDING).count()
        );

        // 🎓 Admissions this month
        res.setAdmissionsThisMonth(
                studentRepo.findAll().stream()
                        .filter(s -> s.getAdmissionDate().isAfter(startMonth.minusDays(1)))
                        .count()
        );

        return res;
    }

    private double getPaid(Long studentId) {
        return feeRepo.findByStudentId(studentId)
                .stream()
                .mapToDouble(FeePayment::getAmountPaid)
                .sum();
    }
}