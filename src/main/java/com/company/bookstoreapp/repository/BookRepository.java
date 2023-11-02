package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    Optional<Book> findById(String bookId);
    void removeBookById(String bookId);
    @Modifying
    @Transactional
    @Query(value = "delete from Book b where b.id =?1 and b.author.id = ?2")
    void deleteByIdAndAuthorId(String id,String authorId);
}
