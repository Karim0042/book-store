package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
