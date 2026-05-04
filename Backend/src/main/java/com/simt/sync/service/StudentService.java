package com.simt.sync.service;

import com.simt.sync.entity.*;
import com.simt.sync.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnquiryRepository enquiryRepository;

    // 🔥 Student ID generator
    public String generateStudentId() {
        LocalDate now = LocalDate.now();

        String month = String.format("%02d", now.getMonthValue());
        String year = String.valueOf(now.getYear()).substring(2);

        long count = studentRepository.count() + 1;

        String sequence = String.format("%04d", count);

        return "SIMT" + month + year + sequence;
    }

    // ➕ Add Student
    public Student addStudent(Student student) {

        student.setStudentId(generateStudentId());
        student.setAdmissionDate(LocalDate.now());

        if (student.getTotalFees() == null) {
            student.setTotalFees(0.0);
        }

        return studentRepository.save(student);
    }

    // 🔄 Convert from enquiry
    public Student convertFromEnquiry(Long enquiryId) {

        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));

        Student student = new Student();

        student.setStudentId(generateStudentId());
        student.setName(enquiry.getStudentName());
        student.setFatherName(enquiry.getFatherName());
        student.setPhone(enquiry.getPhone());
        student.setAddress(enquiry.getAddress());
        student.setCourse(enquiry.getCourse());
        student.setAdmissionDate(LocalDate.now());
        student.setEnquiryId(enquiryId);

        enquiry.setStatus(Status.CONVERTED);
        enquiryRepository.save(enquiry);

        student.setTotalFees(0.0);

        return studentRepository.save(student);
    }

    // ✏️ Update
    public Student updateStudent(Long id, Student updated) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updated.getName());
        student.setFatherName(updated.getFatherName());
        student.setCourse(updated.getCourse());
        student.setTeacherName(updated.getTeacherName());
        student.setTotalFees(updated.getTotalFees());

        return studentRepository.save(student);
    }

    // ❌ Delete
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // 🔍 Search by name
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // 🔍 Search by father name
    public List<Student> searchByFatherName(String name) {
        return studentRepository.findByFatherNameContainingIgnoreCase(name);
    }

    // 📚 Filter by course
    public List<Student> filterByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    // 📅 Filter by date
    public List<Student> filterByDate(LocalDate start, LocalDate end) {
        return studentRepository.findByAdmissionDateBetween(start, end);
    }

    // 📋 Get all
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}