package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Book;
import com.company.bookstoreapp.model.entity.Student;
import com.company.bookstoreapp.model.entity.StudentBook;
import com.company.bookstoreapp.repository.projection.StudentMailProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface StudentBookRepository extends JpaRepository<StudentBook,String> {
    @Query("select b from Book b join StudentBook sb on sb.book.id=b.id where sb.student.id=?1")
    List<Book> findAllBookByStudentId(@PathVariable String id);
    @Query("select s from Student s join StudentBook sb on sb.student.id=s.id where sb.book.id=?1")
    List<Student>finAllStudentByBookId(@PathVariable String id);
    @Modifying
    @Transactional
    @Query(value = "delete from StudentBook sb  where sb.book.id = ?1")
    void deleteAllByBookId(String bookId);
    @Query("select sb.student.account.email as email from StudentBook sb where sb.book.author.id = ?1")
    List<StudentMailProjection> getMail(String authorId);
}
