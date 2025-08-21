package com.nit.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nit.entity.Student;

public interface IStudnetRepository extends JpaRepository<Student, Long> 
{
    List<Student> findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName, String email);
    Page<Student> findAll(Pageable pageable);
}
