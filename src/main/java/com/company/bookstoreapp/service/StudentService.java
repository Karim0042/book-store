package com.company.bookstoreapp.service;

import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.model.dto.response.StudentResponse;
import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.model.entity.Book;
import com.company.bookstoreapp.model.entity.Student;

import java.security.Principal;
import java.util.List;

public interface StudentService {
    void saveStudent(SignUpRequestDTO signUpRequestDTO, Account account);
    Student findById(String id);
    List<BookResponse> getMyBooks(Principal principal);
    List<StudentResponse> getAllReaders(String id);
}
