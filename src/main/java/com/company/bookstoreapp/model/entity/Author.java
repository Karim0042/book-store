package com.company.bookstoreapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "age")
    Integer age;
    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    Set<Book> books;

    @OneToOne
    @JoinColumn(name = "account_id")
    Account account;
}
