package com.simt.sync.controller;

import com.simt.sync.entity.Student;
import com.simt.sync.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ➕ Add student
    @PostMapping("/add")
    public Student add(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // 🔄 Convert enquiry
    @PostMapping("/convert/{id}")
    public Student convert(@PathVariable Long id) {
        return studentService.convertFromEnquiry(id);
    }

    // ✏️ Update
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,
                          @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // ❌ Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Deleted Successfully";
    }

    // 🔍 Search by name
    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return studentService.searchByName(name);
    }

    // 🔍 Search by father name
    @GetMapping("/search-father")
    public List<Student> searchFather(@RequestParam String name) {
        return studentService.searchByFatherName(name);
    }

    // 📚 Filter by course
    @GetMapping("/course")
    public List<Student> filterCourse(@RequestParam String course) {
        return studentService.filterByCourse(course);
    }

    // 📅 Filter by date
    @GetMapping("/filter")
    public List<Student> filter(@RequestParam String start,
                                @RequestParam String end) {

        return studentService.filterByDate(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }

    // 📋 Get all
    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }
}