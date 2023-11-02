package com.company.bookstoreapp.controller;

import com.company.bookstoreapp.model.dto.request.BookCreateRequest;
import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v3/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/create-book")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest, Principal principal) {
        return ResponseEntity.ok(bookService.createBook(bookCreateRequest,principal));
    }
    @DeleteMapping("/delete-book/{bookId}")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId,Principal principal){
        bookService.deleteBook(bookId,principal);
        return ResponseEntity.ok("Book deleted");
    }

}
