package com.company.bookstoreapp.converter;

import com.company.bookstoreapp.model.dto.response.BookResponse;
import com.company.bookstoreapp.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class BookResponseConverter implements Function<Book, BookResponse> {
    @Override
    public BookResponse apply(Book book) {
        return BookResponse.builder()
                .bookName(book.getName())
                .authorName(book.getAuthor()
                        .getName()).build();
    }
}
