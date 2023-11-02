package com.company.bookstoreapp.service;

import com.company.bookstoreapp.model.dto.request.BookCreateRequest;
import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.model.entity.Book;

import java.security.Principal;
import java.util.Optional;

public interface BookService {
        BookResponse createBook(BookCreateRequest bookCreateRequest, Principal principal);
        Optional<Book> findById(String bookId);
        BookResponse readBook(String bookId, Principal principal);
        void deleteBook(String bookId,Principal principal);

}
