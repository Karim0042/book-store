package com.company.bookstoreapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "first_name")
    String name;

    @Column(name = "age")
    Integer age;

    @Column(name = "email")
    String email;
    @ManyToMany
    @JoinTable(
            name = "students_books",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    Set<Book> books;

    @Column(name = "registration_date",columnDefinition = "TIMESTAMP default now()")
    LocalDateTime registrationDate = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "account_id")
    Account account;

}
