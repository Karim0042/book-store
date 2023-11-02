package com.company.bookstoreapp.controller;

import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.model.dto.response.StudentResponse;
import com.company.bookstoreapp.model.entity.Book;
import com.company.bookstoreapp.service.BookService;
import com.company.bookstoreapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentBookController {
    private final BookService bookService;
    private final StudentService studentService;
    @PostMapping("/read-book")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<BookResponse> readBook(@RequestParam String bookId, Principal principal){
        return ResponseEntity.ok(bookService.readBook(bookId,principal));
    }
    @GetMapping("/myBooks")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<BookResponse>> myBooks(Principal principal){
            return ResponseEntity.ok(studentService.getMyBooks(principal));
    }
    @GetMapping("/allReaders/{bookId}")
    public ResponseEntity<List<StudentResponse>> allReadersForSpecificBooks(@PathVariable String bookId){
        return ResponseEntity.ok(studentService.getAllReaders(bookId));
    }
}
