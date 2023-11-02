package com.company.bookstoreapp.service.impl;

import com.company.bookstoreapp.error.Exception.ResourceAlreadyExistsException;
import com.company.bookstoreapp.error.Exception.ResourceNotFoundException;
import com.company.bookstoreapp.model.dto.request.BookCreateRequest;
import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.model.entity.*;
import com.company.bookstoreapp.repository.*;
import com.company.bookstoreapp.repository.projection.StudentMailProjection;
import com.company.bookstoreapp.service.BookService;
import com.company.bookstoreapp.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;
    private final AuthorRepository authorRepository;
    private final StudentRepository studentRepository;
    private final StudentBookRepository studentBookRepository;
    private final MailService mailService;

    @Override
    public BookResponse createBook(BookCreateRequest bookCreateRequest, Principal principal) {
        Account account = accountRepository.findById(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        Author author = authorRepository.findAuthorByAccountId(account.getId());
        Book book = buildBook(bookCreateRequest, author);
        bookRepository.save(book);
         var projection = studentBookRepository.getMail(author.getId());
         if(projection !=null)
         mailService.sendNotification(convertListToArray(projection),"mail sent");
        return getBookCreateResponse(book, author);
    }

    @Override
    public Optional<Book> findById(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public BookResponse readBook(String bookId, Principal principal) {
        Account account = accountRepository.findById(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        Book book = bookRepository.findById(bookId).orElseThrow(ResourceNotFoundException::new);
        Student student = studentRepository.findByAccountId(account.getId());
        student.getBooks().add(book);
        Author author = book.getAuthor();
        StudentBook studentBook = StudentBook
                .builder()
                .student(student)
                .book(book)
                .build();
        studentBookRepository.save(studentBook);

        return getBookCreateResponse(book, author);
    }

    @Override
    public void deleteBook(String bookId, Principal principal) {
        Account account = accountRepository.findById(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        Author author = authorRepository.findAuthorByAccountId(account.getId());
        studentBookRepository.deleteAllByBookId(bookId);
        bookRepository.deleteByIdAndAuthorId(bookId, author.getId());
    }

    private static BookResponse getBookCreateResponse(Book book, Author author) {
        return BookResponse.builder()
                .bookName(book.getName())
                .authorName(author.getName())
                .build();
    }
    private String[] convertListToArray(List<StudentMailProjection> studentEmails) {
        String[] arr = new String[studentEmails.size()];
        for (int i = 0; i < studentEmails.size(); i++) {
            arr[i] = studentEmails.get(i).getEmail();
        }
        return arr;
    }

    private static Book buildBook(BookCreateRequest createRequest, Author author) {
        return Book.builder()
                .author(author)
                .name(createRequest.getName())
                .build();
    }
}
