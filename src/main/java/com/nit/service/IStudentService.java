package com.nit.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nit.entity.Student;

public interface IStudentService 
{
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);

    // New features
    List<Student> searchStudents(String keyword);
    Page<Student> getStudentsPaginated(Pageable pageable);
	
}
