package com.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nit.entity.Student;
import com.nit.service.IStudentService;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/students")
    public String listStudents(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "firstName") String sortField,
                               @RequestParam(defaultValue = "asc") String sortDir,
                               @RequestParam(required = false) String keyword,
                               Model model) {

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        if (keyword != null && !keyword.isEmpty()) {
            List<Student> searchResults = studentService.searchStudents(keyword);
            model.addAttribute("students", searchResults);
            model.addAttribute("keyword", keyword);
        } else {
            Page<Student> studentPage = studentService.getStudentsPaginated(pageable);
            model.addAttribute("students", studentPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", studentPage.getTotalPages());
            model.addAttribute("totalItems", studentPage.getTotalElements());
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        }

        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "create_student";
        }
        studentService.saveStudent(student);
        return "redirect:/students?success";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "edit_student";
        }
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students?updated";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students?deleted";
    }
}
