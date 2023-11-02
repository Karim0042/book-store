package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Book;
import com.company.bookstoreapp.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByAccountId(String accountId);

}
