package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
