package com.simt.sync.repository;

import com.simt.sync.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByFatherNameContainingIgnoreCase(String fatherName);

    List<Student> findByCourse(String course);

    List<Student> findByAdmissionDateBetween(LocalDate start, LocalDate end);
}