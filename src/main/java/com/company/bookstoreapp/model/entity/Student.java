package com.company.bookstoreapp.model.entity;

import com.company.bookstoreapp.model.entity.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator",type= IdGenerator.class)
    String id;
    @Column(name = "name")
    String name;

    @Column(name = "age")
    Integer age;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_books",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"
            )

    )
    Set<Book> books;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "account_id")
    Account account;

}
