package com.company.bookstoreapp.service.impl;

import com.company.bookstoreapp.converter.BookResponseConverter;
import com.company.bookstoreapp.converter.StudentResponseConverter;
import com.company.bookstoreapp.error.Exception.ResourceNotFoundException;
import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.model.dto.response.StudentResponse;
import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.model.entity.Book;
import com.company.bookstoreapp.model.entity.Student;
import com.company.bookstoreapp.model.entity.StudentBook;
import com.company.bookstoreapp.repository.AccountRepository;
import com.company.bookstoreapp.repository.StudentBookRepository;
import com.company.bookstoreapp.repository.StudentRepository;
import com.company.bookstoreapp.service.BookService;
import com.company.bookstoreapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final BookService bookService;
    private final AccountRepository accountRepository;
    private final StudentBookRepository studentBookRepository;
    private final BookResponseConverter bookResponseConverter;
    private final StudentResponseConverter studentResponseConverter;
    @Override
    public void saveStudent(SignUpRequestDTO signUpRequestDTO, Account account) {
        Student student = buildNewUser(signUpRequestDTO,account);
        studentRepository.save(student);
    }

    @Override
    public Student findById(String id) {
        return studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<BookResponse> getMyBooks(Principal principal) {
        Account account = accountRepository.findById(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        Student student = studentRepository.findByAccountId(account.getId());

        return studentBookRepository.findAllBookByStudentId(student.getId()).stream().map(bookResponseConverter).toList();
    }

    @Override
    public List<StudentResponse> getAllReaders(String id) {
        return studentBookRepository.finAllStudentByBookId(id).stream().map(studentResponseConverter).toList();
    }


    private Student buildNewUser(SignUpRequestDTO userSignUpRequest, Account account) {
        return Student.builder()
                .account(account)
                .age(userSignUpRequest.getAge())
                .name(userSignUpRequest.getName())
                .build();
    }
    private BookResponse build(Book book){
        return BookResponse.builder()
                .bookName(book.getName())
                .authorName(book.getAuthor()
                        .getName())
                .build();
    }
}
